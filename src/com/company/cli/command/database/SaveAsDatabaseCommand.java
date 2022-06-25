package com.company.cli.command.database;

import com.company.Database;

public class SaveAsDatabaseCommand extends DatabaseCommand{
    public SaveAsDatabaseCommand() {
        super(1);
    }

    @Override
    protected void run(String[] args) {
        Database.getInstance().createFile(args[0]);
        System.out.println("Successfully saved at: "+args[0]);
    }
}
