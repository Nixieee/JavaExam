package com.company;

public class Main {

    public static void main(String[] args) throws Exception {
        //Database database = new Database("test");
        //String n = System.console().readLine();
        Table table = new Table("Person");
        Column column = new Column("name", TypeOfData.STRING);
        Column surname = new Column("surname", TypeOfData.STRING);
        Column age = new Column("age", TypeOfData.INTEGER);
        table.addColumns(column);
        table.addColumns(surname);
        table.addColumns(age);
        Row row = new Row();
        row.addCell(table.getColumn("name"), "a");
        row.addCell(table.getColumn("surname"), "ivanov");
        row.addCell(table.getColumn("age"), "2");
        table.addRows(row);
        Row newRow = new Row();
        newRow.addCell(table.getColumn("name"), "georgi");
        newRow.addCell(table.getColumn("surname"), "georgiev");
        newRow.addCell(table.getColumn("age"), "55");
        table.addRows(newRow);
        //System.out.println(table.toString());
        //System.out.println(table.describe());
        //table.update(0,"ivan",2,"22");
        //table.delete(0,"shopara");
        //System.out.println(table.count(0,"shopara"));
        //System.out.println(table.aggregate(0,"ivan",2,"product"));
        //System.out.println(table.toString());
XMLParser xmlParser = new XMLParser();
xmlParser.XMLParse("database",table);
        //System.out.println(table.getRows().get(0));



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
