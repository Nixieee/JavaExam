package com.company.cli.command;

public class InvalidCommandCommand extends Command {

    private final String commandName;

    public InvalidCommandCommand(String commandName) {
        super(0);
        this.commandName = commandName;
    }

    @Override
    protected void run(String[] args) {
        if (commandName.trim().equals("")) {
            System.out.println("Please input a command.");
        } else {
            System.out.printf("%s command doesn't exist!%n", commandName);
        }
    }
}
