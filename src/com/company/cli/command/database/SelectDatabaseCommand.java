package com.company.cli.command.database;

import com.company.Database;
import com.company.Table;

public class SelectDatabaseCommand extends DatabaseCommand{
    public SelectDatabaseCommand() {
        super(3);
    }

    @Override
    protected void run(String[] args) {
        Table table = Database.getInstance().getTable(args[2]);
        if (table != null)
            System.out.println(table.select(args[0], args[1]));
    }
}
