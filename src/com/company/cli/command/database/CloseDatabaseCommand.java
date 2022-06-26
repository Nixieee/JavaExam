package com.company.cli.command.database;

import com.company.database.Database;

public class CloseDatabaseCommand extends DatabaseCommand {
    public CloseDatabaseCommand() {
        super(0);
    }

    @Override
    protected void run(String[] args) {
        Database.getInstance().close();
        System.out.println("Database closed.");
    }
}
