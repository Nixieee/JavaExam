package com.company.cli.command.database;

import com.company.database.Database;
import com.company.table.Table;
import com.company.xml.io.TableXMLParser;

public class ExportTableDatabaseCommand extends DatabaseCommand{
    public ExportTableDatabaseCommand() {
        super(2);
    }

    @Override
    protected void run(String[] args) {
        Table table = Database.getInstance().getTable(args[0]);
        if (table != null)
            TableXMLParser.XMLParse(table, args[1]);
    }
}
