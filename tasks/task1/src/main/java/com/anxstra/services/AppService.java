package com.anxstra.services;

import com.anxstra.entities.Credit;
import com.anxstra.entities.User;
import com.anxstra.entities.enums.SortBy;
import com.anxstra.gson.config.AppConfigurer;
import com.anxstra.gson.config.DBReader;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.anxstra.utils.TableFormatter.HEADER;
import static com.anxstra.utils.TableFormatter.ROW_FORMAT;
import static com.anxstra.utils.TableFormatter.SEPARATOR_ROW;
import static com.anxstra.utils.TableFormatter.formatRepaymentDay;

public class AppService {

    private final Map<SortBy, Comparator<Credit>> comparators;

    private final UserService userService;

    private final CreditService creditService;

    public AppService() {
        this.userService = new UserService();
        this.creditService = new CreditService();
        this.comparators = new EnumMap<>(SortBy.class);

        comparators.put(SortBy.NAME, Comparator.comparing(cr -> userService.findById(cr.getUserId())
                                                                           .getFullName()));
        comparators.put(SortBy.DEBT, Comparator.comparing(Credit::getMoney));
        comparators.put(SortBy.AGE, Comparator.comparing(Credit::getDate));
    }

    public void calculateUsersCredits() {
        DBReader.initDB();
        List<User> users = userService.findAllByShowType();
        List<Credit> credits = new LinkedList<>();
        for (User user : users) {
            credits.addAll(creditService.processUserCredits(user));
        }
        credits.sort(comparators.get(AppConfigurer.getSetting()
                                                  .getSortBy()));
        printToConsole(credits);
    }

    private void printToConsole(List<Credit> credits) {
        String table = SEPARATOR_ROW + HEADER;
        table += credits.stream()
                        .map(credit -> {
                            String name = userService.findById(credit.getUserId())
                                                     .getFullName();
                            return String.format(ROW_FORMAT, credit.getId(), credit.getUserId(),
                                    name, credit.getProcessedTransactions(), credit.getMoney(),
                                    credit.getPeriod(), formatRepaymentDay(credit.getRepaymentDate()));
                        })
                        .collect(Collectors.joining(SEPARATOR_ROW, SEPARATOR_ROW, SEPARATOR_ROW));

        System.out.println(table);
    }

}
