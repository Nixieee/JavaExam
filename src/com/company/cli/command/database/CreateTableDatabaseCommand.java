package com.company.cli.command.database;

import com.company.database.Database;
import com.company.table.Table;

public class CreateTableDatabaseCommand extends DatabaseCommand{

    public CreateTableDatabaseCommand() {
        super(1);
    }

    @Override
    protected void run(String[] args) {
        String tableName = args[0];
        Table newTable = new Table(tableName);
        Database.getInstance().addTable(newTable);
        System.out.printf("Table %s created%n", tableName);
    }
}
