package api.command;

public class CommandInvoker {
    public void executeCommand(ICommand command) {
        command.execute();
    }
}