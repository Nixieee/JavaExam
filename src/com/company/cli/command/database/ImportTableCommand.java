package com.company.cli.command.database;

import com.company.Database;

public class ImportTableCommand extends DatabaseCommand {

    public ImportTableCommand() {
        super(1);
    }

    @Override
    protected void run(String[] args) {
        Database.getInstance().addTableFromFile(args[0]);
    }
}
