package com.company.database;
import com.company.table.Table;
import com.company.xml.io.TableXMLParser;

import java.util.*;



public class Database {
    private final String name;
    private final ArrayList<Table> listofTables = new ArrayList<>();
    static Database instance;


    private Database(String name) {
        this.name = name;
    }

    public void addTable(Table table) {
        listofTables.add(table);
    }


    public static Database getInstance(){
        return instance;
    }

    public static Database initialize(String name){
        instance = new Database(name);
        return instance;
    }

    public void close()
    {
        instance = null;
    }

    public void addTableFromFile(String fileLocation) {
        try {
            if (!listofTables.contains(TableXMLParser.queryTable(fileLocation))) {
                if (TableXMLParser.queryTable(fileLocation) != null) {
                    listofTables.add(TableXMLParser.queryTable(fileLocation));
                    System.out.println("Successfully imported table " + Objects.requireNonNull(TableXMLParser.queryTable(fileLocation)).getTableName());
                } else System.out.println("Invalid file!");
            } else System.out.println("Table already exists!");
        }catch (Exception e) {
            throw new RuntimeException("Cannot add table from file", e);
        }
    }

    public ArrayList<Table> getTables(){

      return listofTables;
    }

    public Table getTable(String tableName){
        for(Table t : listofTables){
            if (t.getTableName().equals(tableName)){
                return t;
            }
        }
        System.out.println("There is no such table!");
        return null;
    }

    public String getName() {
        return name;
    }

    public void rename(String oldName, String newName) {
        boolean flag = false;
        boolean error = true;
        for (Table t : listofTables) {
            if (t.getTableName().equals(newName)) {
                flag = true;
                break;
            }
        }
        for (Table t : listofTables) {
            if (!flag && t.getTableName().equals(oldName)) {
                t.setTableName(newName);
                error = false;
            }
        }
        if (error) {
            System.out.println("There is a table with that name already!");
        }
    }

    public void importTable(Table table){
        listofTables.add(table);
    }

}

