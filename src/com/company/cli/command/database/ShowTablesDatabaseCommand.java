package com.company.cli.command.database;

import com.company.Database;

public class ShowTablesDatabaseCommand extends DatabaseCommand{

    public ShowTablesDatabaseCommand() {
        super(0);
    }

    @Override
    protected void run(String[] args) {
        Database.getInstance().getTables();
    }
}
