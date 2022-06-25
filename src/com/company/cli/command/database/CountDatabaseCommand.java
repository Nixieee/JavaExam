package com.company.cli.command.database;

import com.company.Database;
import com.company.Table;

public class CountDatabaseCommand extends DatabaseCommand{
    public CountDatabaseCommand() {
        super(3);
    }

    @Override
    protected void run(String[] args) {
        Table table = Database.getInstance().getTable(args[0]);
        System.out.println(table.count(args[1], args[2]));
    }
}
