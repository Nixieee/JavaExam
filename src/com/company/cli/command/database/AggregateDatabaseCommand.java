package com.company.cli.command.database;

import com.company.Database;
import com.company.Table;

public class AggregateDatabaseCommand extends DatabaseCommand{
    public AggregateDatabaseCommand() {
        super(5);
    }

    @Override
    protected void run(String[] args) {
        Table table = Database.getInstance().getTable(args[0]);
        table.aggregate(args[1], args[2], args[3], args[4]);
    }
}
