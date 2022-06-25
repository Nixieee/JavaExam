package com.company.cli.command;

import com.company.Database;

public abstract class Command {

    protected final int minimumArgumentCount;

    protected Command(int minimumArgumentCount) {
        this.minimumArgumentCount = minimumArgumentCount;
    }

    public void execute(String[] args) {
        if(areArgumentsValid(args)) {
            run(args);
        }
    }
    protected abstract void run(String[] args);

    private boolean areArgumentsValid(String[] args) {
        if (args.length < minimumArgumentCount) {
            System.out.printf("Not enough arguments: specified %d, required %d%n", args.length, minimumArgumentCount);
            return false;
        }
        return true;
    }
}
