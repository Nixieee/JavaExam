package com.company.cli.command.database;

import com.company.table.Column;
import com.company.database.Database;
import com.company.table.Row;
import com.company.table.Table;

import java.util.Scanner;

public class InsertDatabaseCommand extends DatabaseCommand{
    public InsertDatabaseCommand() {
        super(2);
    }

    @Override
    protected void run(String[] args) {
        Table table = Database.getInstance().getTable(args[0]);
        if (table != null) {
            Row row = new Row();
            for (Column c : table.getListOfColumns()) {
                row.addCell(c, null);
            }
            int j = 0;
            System.out.print("values:");
            Scanner input = new Scanner(System.in);
            String[] values = input.nextLine().trim().split(" ");
            if (values.length == args.length - 1) {
                for (int i = 1; i < args.length; i++) {
                    if(!row.addCell(table.getColumn(args[i]), values[j])){
                        System.out.println("Fails to insert row");
                        return;
                    }
                    j++;
                }
                    table.addRows(row);
                    System.out.println("Successfully inserted a new row!");
            }else System.out.println("Invalid amount of values!");
        }
    }
}
