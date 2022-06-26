package com.company.cli.command.database;

import com.company.database.Database;
import com.company.table.Table;
import com.company.table.TableOperations;

public class AggregateDatabaseCommand extends DatabaseCommand{
    public AggregateDatabaseCommand() {
        super(5);
    }

    @Override
    protected void run(String[] args) {
        Table table = Database.getInstance().getTable(args[0]);
        TableOperations.aggregate(table,args[1], args[2], args[3], args[4]);
    }
}
