package com.company.cli.command.database;

import com.company.database.Database;
import com.company.table.Table;

public class PrintDatabaseCommand extends DatabaseCommand{
    public PrintDatabaseCommand() {
        super(1);
    }

    @Override
    protected void run(String[] args) {
        Table table = Database.getInstance().getTable(args[0]);
        if (table != null)
            System.out.println(table.print());
    }
}
