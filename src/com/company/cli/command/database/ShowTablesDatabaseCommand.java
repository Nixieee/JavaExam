package com.company.cli.command.database;

import com.company.database.Database;
import com.company.table.Table;

public class ShowTablesDatabaseCommand extends DatabaseCommand{

    public ShowTablesDatabaseCommand() {
        super(0);
    }

    @Override
    protected void run(String[] args) {
        for(Table t :Database.getInstance().getTables())
        {
            System.out.println(t.getTableName());
        }

    }
}
