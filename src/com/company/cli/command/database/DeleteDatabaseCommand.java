package com.company.cli.command.database;

import com.company.Database;
import com.company.Table;

public class DeleteDatabaseCommand extends DatabaseCommand{
    protected DeleteDatabaseCommand() {
        super(3);
    }

    @Override
    protected void run(String[] args) {
        Table table = Database.getInstance().getTable(args[0]);
        if (table != null)
            table.delete(args[1], args[2]);
        System.out.println("Successfully deleted!");
    }
}
