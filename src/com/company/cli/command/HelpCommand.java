package com.company.cli.command;

public class HelpCommand extends Command {

    public HelpCommand() {
        super(0);
    }

    @Override
    protected void run(String[] args) {
        System.out.println("The following commands are supported: \n" +
                "open <file> opens <file> \n" +
                "close closes currently opened file \n" +
                "save saves the currently open file \n" +
                "saveas <file> saves the currently open file in <file> \n" +
                "help prints this information  \n" +
                "exit exits the program\n");
    }
}
