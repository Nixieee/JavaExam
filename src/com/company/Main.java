package com.company;
import java.util.*;
public class Main {

    public static void main(String[] args) throws Exception {
        //Database database = new Database("test");
        //String n = System.console().readLine();
        Table table = new Table();
        Cells cells = new Cells();
        Column column = new Column("name", TypeOfData.STRING);
        Column surname = new Column("surname", TypeOfData.STRING);
        Column age = new Column("age", TypeOfData.INTEGER);
        table.addColumns(column);
        table.addColumns(surname);
        table.addColumns(age);
        cells.addCell(table.getColumn("name"), "a");
        cells.addCell(table.getColumn("surname"), "ivanov");
        cells.addCell(table.getColumn("age"), "2");
        Row row = new Row(cells.getCells());
        table.addRows(row);
        Cells newRow = new Cells();
        newRow.addCell(table.getColumn("name"),"ivan" );
        newRow.addCell(table.getColumn("surname"), "ivanov");
        newRow.addCell(table.getColumn("age"), "22");
        row = new Row(newRow.getCells());
        table.addRows(row);
        Cells newRow1 = new Cells();
        newRow1.addCell(table.getColumn("name"),"ivan" );
        newRow1.addCell(table.getColumn("surname"), "ivanov");
        newRow1.addCell(table.getColumn("age"), "34");
        row = new Row(newRow1.getCells());
        table.addRows(row);
        //System.out.println(table.toString());
        //System.out.println(table.describe());
        //System.out.println(table.select(1,"hui"));
        table.update(0,"ivan",2,"33");
        //table.delete(0,"shopara");
        System.out.println(table.count(0,"shopara"));
        System.out.println(table.toString());




/*
    switch (n){
        case "insert":
        {

        }
        case "print":
        tablename.getRows();
    }


 */
    }
}
