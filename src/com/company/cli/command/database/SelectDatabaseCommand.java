package com.company.cli.command.database;

import com.company.database.Database;
import com.company.table.Table;
import com.company.table.TableOperations;

public class SelectDatabaseCommand extends DatabaseCommand{
    public SelectDatabaseCommand() {
        super(3);
    }

    @Override
    protected void run(String[] args) {
        Table table = Database.getInstance().getTable(args[2]);
        if (table != null)
            System.out.println(TableOperations.select(table,args[0], args[1]));
    }
}
