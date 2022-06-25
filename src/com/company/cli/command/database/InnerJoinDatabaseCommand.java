package com.company.cli.command.database;

import com.company.Database;
import com.company.Table;

public class InnerJoinDatabaseCommand extends DatabaseCommand{
    public InnerJoinDatabaseCommand() {
        super(4);
    }

    @Override
    protected void run(String[] args) {
        Table tableOne = Database.getInstance().getTable(args[0]);
        Table tableTwo = Database.getInstance().getTable(args[2]);
        if(tableOne != null && tableTwo != null) {
            Table innerJoinedTable = tableOne.innerJoin(tableOne, args[1], tableTwo, args[3]);
            Database.getInstance().addTable(innerJoinedTable);
            System.out.println(innerJoinedTable.getTableName());
        }
    }
}
