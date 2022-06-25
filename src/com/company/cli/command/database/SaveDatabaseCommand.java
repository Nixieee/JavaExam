package com.company.cli.command.database;

import com.company.Database;

public class SaveDatabaseCommand extends DatabaseCommand {
    public SaveDatabaseCommand() {
        super(0);
    }

    @Override
    protected void run(String[] args) {
        Database database = Database.getInstance();
        database.createFile(database.getName());
        System.out.println("Successfully saved!");
    }
}
