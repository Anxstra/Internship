package com.anxstra.gson.config;

import com.anxstra.entities.Discount;
import com.anxstra.gson.adapters.DiscountAdapter;
import com.anxstra.gson.adapters.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.util.Objects;

public class GsonConfigurer {

    private static Gson gson;

    private GsonConfigurer() {
    }

    public static Gson getGson() {
        if (Objects.isNull(gson)) {
            gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe())
                                    .registerTypeAdapter(Discount.class, new DiscountAdapter().nullSafe())
                                    .setPrettyPrinting()
                                    .create();
        }
        return gson;
    }
}
