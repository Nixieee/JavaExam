package com.company.cli.command.database;

import com.company.Database;
import com.company.Table;
import com.company.TableXMLParser;

public class ExportDatabaseCommand extends DatabaseCommand{
    public ExportDatabaseCommand() {
        super(2);
    }

    @Override
    protected void run(String[] args) {
        Table table = Database.getInstance().getTable(args[0]);
        if (table != null)
            TableXMLParser.XMLParse(table, args[1]);
    }
}
