package com.company;

import java.util.*;
import java.io.File;
import java.io.IOException;

public class Database {
    private final String name;
    ArrayList<Table> listofTables = new ArrayList<>();


    public Database(String name){
        this.name = name;
        createFile(name);
    }

    private void createFile(String name){
        try {
            File myObj = new File(name+".xml");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void importTable(Table table){
        this.listofTables.add(table);
    }

}

