package view;

import beans.Employee;
import beans.EmployeeBuilder;
import dao.EmployeeDaoImpl;
import dao.IEmployeeDao;
import dao.decorators.LoggingEmployeeDaoDecorator;
import dao.proxy.EmployeeDaoProxy;
import service.EmployeeServiceImpl;
import service.IEmployeeService;

public class EmployeeView {

    // Singleton Pattern
    private static EmployeeView instance;

    private EmployeeView(IEmployeeService employeeService) {
        // Bridge Pattern: View depends on Service interface
        this.employeeService = employeeService;
    }

    public static synchronized EmployeeView getInstance(IEmployeeService employeeService) {
        if (instance == null) {
            instance = new EmployeeView(employeeService);
        }
        return instance;
    }

    private final IEmployeeService employeeService;

    public void showAllEmployees() {
        System.out.println("--- Liste des employés ---");
        employeeService.trouverTousLesEmployes().forEach(e -> System.out.println("ID: " + e.getId() + ", Nom: " + e.getNom()));
        System.out.println("--------------------------");
    }

    public static void main(String[] args) {
        // --- Composition Root: Assembling the application ---

        // 1. Create the core DAO (Singleton)
        IEmployeeDao dao = EmployeeDaoImpl.getInstance();

        // 2. Decorate it with logging (Decorator Pattern)
        IEmployeeDao loggingDao = new LoggingEmployeeDaoDecorator(dao);

        // 3. Secure it with a proxy (Proxy Pattern)
        IEmployeeDao secureDao = new EmployeeDaoProxy(loggingDao);

        // 4. Create the service with the final DAO implementation (Singleton + Bridge)
        IEmployeeService service = EmployeeServiceImpl.getInstance(secureDao);

        // 5. Create the view (Singleton + Bridge)
        EmployeeView view = EmployeeView.getInstance(service);

        // --- Using the application ---

        // Create an employee using the Builder Pattern
        Employee emp1 = new EmployeeBuilder(1, "Dupont").prenom("Jean").poste("Développeur").build();
        service.creerEmploye(emp1);

        view.showAllEmployees();
    }
}
