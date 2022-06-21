package com.company;
import java.util.Scanner;
public class Menu {


    public void menu() throws Exception {
        Scanner input = new Scanner(System.in);
        do {
            input = new Scanner(System.in);
            String []temp = input.nextLine().split(" ");

            switch (temp[0]) {
                case "open":
                    submenu(temp[1]);
                    break;
                case "help":
                    System.out.println("The following commands are supported: " +
                            "open <file> opens <file> " +
                            "close closes currently opened file " +
                            "save saves the currently open file" +
                            "saveas <file> saves the currently open file in <file>" +
                            "help prints this information  " +
                            "exit exists the program");
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Sorry, please enter valid command");
            }
      } while (!input.toString().equals("exit"));

    }

    private void submenu(String databaseName) throws Exception {
        Database database = new Database();
        database.openFile(databaseName);
        Scanner input = new Scanner(System.in);
        do {
            input = new Scanner(System.in);
            String []temp = input.nextLine().split(" ");
            switch (temp[0]) {
                case "import":
                    if(temp.length>1)
                    database.addTableFromFile(temp[1]);
                    else System.out.println("Too few arguments");
                    break;
                case "createtable":
                    if(temp.length>1) {
                        Table newTable = new Table(temp[1]);
                        database.addTable(newTable);
                        System.out.println("Successfully created table" + temp[1]);
                    }else System.out.println("Too few arguments");
                    break;
                case "showtables":
                    database.getTables();
                    break;
                case "describe":
                    if(temp.length>1) {
                        Table table = database.getTable(temp[1]);
                        if (table != null) {
                            System.out.println(table.describe());
                        }
                    }else System.out.println("Too few arguments");
                    break;
                case "print":
                    if(temp.length>1) {
                        Table table = database.getTable(temp[1]);
                        if (table != null)
                            System.out.println(table.print());
                    }else System.out.println("Too few arguments");
                    break;
                case "export":
                    if(temp.length>=3) {
                        Table table = database.getTable(temp[1]);
                        if (table != null)
                            TableXMLParser.XMLParse(table, temp[2]);
                    }else System.out.println("Too few arguments");
                    break;
                case "select":
                    if(temp.length>=4) {
                        Table table = database.getTable(temp[3]);
                        if (table != null)
                            System.out.println(table.select(Integer.parseInt(temp[1]), temp[2]));
                    }else System.out.println("Too few arguments");
                    break;
                case "addcolumn":
                    if(temp.length>=4) {
                        Table table = database.getTable(temp[1]);
                        if (table != null) {
                            table.addColumns(new Column (temp[2], TypeOfData.valueOf(temp[3].toUpperCase())));
                        }
                    }else System.out.println("Too few arguments");
                    break;
                case "update":
                    if(temp.length>=6) {
                        Table table = database.getTable(temp[1]);
                        if (table != null)
                            table.update(Integer.parseInt(temp[2]), temp[3], Integer.parseInt(temp[4]), temp[5]);
                    }else System.out.println("Too few arguments");
                    break;
                case "delete":
                    if(temp.length>=4) {
                        Table table = database.getTable(temp[1]);
                        if (table != null)
                            table.delete(Integer.parseInt(temp[2]), temp[3]);
                    }else System.out.println("Too few arguments");
                    break;
                case "insert":
                    if(temp.length>=3) {
                        Table table = database.getTable(temp[1]);
                        boolean flag = true;
                        if (table != null) {
                            Row row = new Row();
                            for (Column c : table.getListOfColumns()) {
                                row.addCell(c, null);
                            }
                            int j = 0;
                            System.out.print("values:");
                            input = new Scanner(System.in);
                            String[] values = input.nextLine().split(" ");
                            for (int i = 2; i < temp.length; i++) {
                                flag = row.addCell(table.getColumn(temp[i]), values[j]);
                                j++;
                            }
                            if (flag) table.addRows(row);
                        }
                    }else System.out.println("Too few arguments");
                    break;
                case "innerjoin":
                    if(temp.length>=4) {
                        Table tableOne = database.getTable(temp[1]);
                        Table tableTwo = database.getTable(temp[3]);
                        Table innerJoinedTable = tableOne.innerJoin(tableOne, temp[2], tableTwo, temp[4]);
                        database.addTable(innerJoinedTable);
                        System.out.println(innerJoinedTable.getTableName());
                    }else System.out.println("Too few arguments");
                    break;
                case "rename":
                    if(temp.length>=3) {
                        Table table = database.getTable(temp[1]);
                        table.rename(temp[1], temp[2]);
                    }else System.out.println("Too few arguments");
                    break;
                case "count":
                    if(temp.length>=3) {
                        Table table = database.getTable(temp[1]);
                        table.count(Integer.parseInt(temp[2]), temp[3]);
                    }else System.out.println("Too few arguments");
                    break;
                case "aggregate":
                    if(temp.length>=5) {
                        Table table = database.getTable(temp[1]);
                        table.aggregate(Integer.parseInt(temp[2]), temp[3], Integer.parseInt(temp[4]), temp[5]);
                    }else System.out.println("Too few arguments");
                    break;
                case "save":
                    database.createFile(databaseName);
                    break;
                case "saveas":
                    if(temp.length>1)
                    database.createFile(temp[1]);
                    break;
                case "close":
                    return;
                default:
                    System.out.println("Sorry, please enter valid command");
            }
        } while (!input.toString().equals("close"));
    }
}