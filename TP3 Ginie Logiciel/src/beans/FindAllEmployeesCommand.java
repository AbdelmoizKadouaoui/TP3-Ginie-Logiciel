package api.command;

import service.IEmployeeService;

public class FindAllEmployeesCommand implements ICommand {
    private final IEmployeeService service;

    public FindAllEmployeesCommand(IEmployeeService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        System.out.println("COMMAND: Exécution de FindAllEmployees...");
        service.trouverTousLesEmployes();
    }
}