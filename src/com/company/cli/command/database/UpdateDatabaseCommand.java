package com.company.cli.command.database;

import com.company.database.Database;
import com.company.table.Table;
import com.company.table.TableOperations;

public class UpdateDatabaseCommand extends DatabaseCommand{
    public UpdateDatabaseCommand() {
        super(5);
    }

    @Override
    protected void run(String[] args) {
        Table table = Database.getInstance().getTable(args[0]);
        if (table != null)
            TableOperations.update(table,args[1], args[2], args[3], args[4]);
        System.out.println("Successfully updated!");
    }
}
