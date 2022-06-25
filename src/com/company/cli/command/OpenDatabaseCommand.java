package com.company.cli.command;

import com.company.Database;

public class OpenDatabaseCommand extends Command {

    public OpenDatabaseCommand() {
        super(1);
    }

    @Override
    protected void run(String[] args) {
        String databaseName = args[0];
        if(Database.getInstance() == null) {
            Database database = Database.initialize(databaseName);
            database.openFile();
        }
        else System.out.println("Database already opened!");

    }
}
