package com.company.cli.command.database;

import com.company.Database;

public class RenameDatabaseCommand extends DatabaseCommand{
    public RenameDatabaseCommand() {
        super(2);
    }

    @Override
    protected void run(String[] args) {
        Database.getInstance().rename(args[0], args[1]);
        System.out.println("Successfully renamed table "+args[0]+" as: "+args[1]);
    }
}
