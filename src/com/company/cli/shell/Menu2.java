package com.company.cli.shell;

import com.company.cli.command.Command;
import com.company.cli.command.HelpCommand;
import com.company.cli.command.InvalidCommandCommand;
import com.company.cli.command.OpenDatabaseCommand;
import com.company.cli.command.database.*;

import java.util.*;

public class Menu2 {

    private static final Map<String, Command> knownCommands = knownCommands();


    public static void commandLineInterface() {
        Scanner input = new Scanner(System.in);
        while(true) {
            String inputText = input.nextLine();
            if (inputText.trim().equalsIgnoreCase("exit")) {
                break;
            }
            String[] temp = inputText.split(" ");
            String commandIdentifier = temp[0];
            String[] arguments = Arrays.copyOfRange(temp, 1, temp.length);
            Command com = knownCommands.get(commandIdentifier);
            if (com == null){
                com = new InvalidCommandCommand(commandIdentifier);
            }
            com.execute(arguments);
        }
        System.out.println("Exiting the program.");
    }

    private static Map<String, Command> knownCommands() {
        Map<String, Command> knownCommands = new HashMap<>();
        knownCommands.put("open", new OpenDatabaseCommand());
        knownCommands.put("help", new HelpCommand());
        knownCommands.put("import", new ImportTableCommand());
        knownCommands.put("createtable", new CreateTableDatabaseCommand());
        knownCommands.put("showtables", new ShowTablesDatabaseCommand());
        knownCommands.put("describe", new DescribeDatabaseCommand());
        knownCommands.put("print", new PrintDatabaseCommand());
        knownCommands.put("delete",new DeleteDatabaseCommand());
        knownCommands.put("export", new ExportTableDatabaseCommand());
        knownCommands.put("select", new SelectDatabaseCommand());
        knownCommands.put("addcolumn", new AddColumnDatabaseCommand());
        knownCommands.put("update", new UpdateDatabaseCommand());
        knownCommands.put("insert", new InsertDatabaseCommand());
        knownCommands.put("innerjoin", new InnerJoinDatabaseCommand());
        knownCommands.put("rename", new RenameDatabaseCommand());
        knownCommands.put("count", new CountDatabaseCommand());
        knownCommands.put("aggregate", new AggregateDatabaseCommand());
        knownCommands.put("save", new SaveDatabaseCommand());
        knownCommands.put("saveas", new SaveAsDatabaseCommand());
        knownCommands.put("close", new CloseDatabaseCommand());
        return knownCommands;
    }
}
