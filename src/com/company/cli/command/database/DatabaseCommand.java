package com.company.cli.command.database;

import com.company.Database;
import com.company.cli.command.Command;

public abstract class DatabaseCommand extends Command {
    protected DatabaseCommand(int minimumArgumentCount) {
        super(minimumArgumentCount);
    }


    @Override
    public void execute(String[] args) {
        if (isDatabaseOpen()) {
            super.execute(args);
        }
    }

    private boolean isDatabaseOpen() {
        if(Database.getInstance() == null) {
            System.out.println("Database not open!");
            return false;
        }
        return true;
    }
}
