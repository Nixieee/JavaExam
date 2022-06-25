package com.company.cli.command.database;

import com.company.Column;
import com.company.Database;
import com.company.Table;
import com.company.TypeOfData;

public class AddColumnDatabaseCommand extends DatabaseCommand{
    public AddColumnDatabaseCommand() {
        super(3);
    }

    @Override
    protected void run(String[] args) {
        Table table = Database.getInstance().getTable(args[0]);
        if (table != null) {
            table.addColumns(new Column(args[1], TypeOfData.valueOf(args[2].toUpperCase())));
            System.out.println("Successfully added column ");
        }
    }
}
