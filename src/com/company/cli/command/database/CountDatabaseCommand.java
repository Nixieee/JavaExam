package com.company.cli.command.database;

import com.company.database.Database;
import com.company.table.Table;
import com.company.table.TableOperations;

public class CountDatabaseCommand extends DatabaseCommand{
    public CountDatabaseCommand() {
        super(3);
    }

    @Override
    protected void run(String[] args) {
        Table table = Database.getInstance().getTable(args[0]);
        TableOperations.count(table,args[1], args[2]);
    }
}
