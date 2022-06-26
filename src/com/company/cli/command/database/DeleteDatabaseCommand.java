package com.company.cli.command.database;

import com.company.database.Database;
import com.company.table.Table;
import com.company.table.TableOperations;

public class DeleteDatabaseCommand extends DatabaseCommand{
    public DeleteDatabaseCommand() {
        super(3);
    }

    @Override
    protected void run(String[] args) {
        Table table = Database.getInstance().getTable(args[0]);
        if (table != null)
        TableOperations.delete(table,args[1], args[2]);
        System.out.println("Successfully deleted!");
    }
}
