package com.anxstra;

import com.anxstra.services.AppService;

public class Executor {

    private final AppService appService;

    private Executor(AppService appService) {
        this.appService = appService;
    }

    public static void main(String[] args) {
        Executor executor = new Executor(new AppService());
        executor.execute();
    }

    private void execute() {
        appService.calculateUsersCredits();
    }
}