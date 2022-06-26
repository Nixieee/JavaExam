package com.company.cli.command.database;

import com.company.table.Column;
import com.company.database.Database;
import com.company.table.Table;
import com.company.table.TypeOfData;

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
