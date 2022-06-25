package com.company.cli.command.database;

import com.company.Database;
import com.company.Table;

public class UpdateDatabaseCommand extends DatabaseCommand{
    public UpdateDatabaseCommand() {
        super(5);
    }

    @Override
    protected void run(String[] args) {
        Table table = Database.getInstance().getTable(args[0]);
        if (table != null)
            table.update(args[1], args[2], args[3], args[4]);
        System.out.println("Successfully updated!");
    }
}
