package com.company.cli.command;

import com.company.database.Database;
import com.company.xml.io.DatabaseXMLParser;

public class OpenDatabaseCommand extends Command {

    public OpenDatabaseCommand() {
        super(1);
    }

    @Override
    protected void run(String[] args) {
        String databaseName = args[0];
        if(Database.getInstance() == null) {
            Database database = Database.initialize(databaseName);
            DatabaseXMLParser.openFile(database);
            System.out.println("Successfully opened "+databaseName);
        }
        else System.out.println("Database already opened!");

    }
}
