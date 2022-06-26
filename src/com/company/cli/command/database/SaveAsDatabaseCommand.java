package com.company.cli.command.database;

import com.company.database.Database;
import com.company.table.Table;
import com.company.xml.io.DatabaseXMLParser;
import com.company.xml.io.TableXMLParser;

public class SaveAsDatabaseCommand extends DatabaseCommand{
    public SaveAsDatabaseCommand() {
        super(1);
    }

    @Override
    protected void run(String[] args) {
        Database database = Database.getInstance();
        DatabaseXMLParser.createFile(database,args[0]);
        for(Table t : database.getTables())
        {
            TableXMLParser.XMLParse(t,t.getTableName()+".xml");
        }
        System.out.println("Successfully saved at: "+args[0]);
    }
}
