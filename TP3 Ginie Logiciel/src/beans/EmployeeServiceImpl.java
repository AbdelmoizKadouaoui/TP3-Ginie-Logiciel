package service;

import beans.Employee;
import dao.IEmployeeDao;

import java.util.List;

public class EmployeeServiceImpl implements IEmployeeService {

    // Singleton Pattern
    private static EmployeeServiceImpl instance;

    private EmployeeServiceImpl(IEmployeeDao employeeDao) {
        // Bridge Pattern: Service depends on DAO interface, not implementation.
        this.employeeDao = employeeDao;
    }

    public static synchronized EmployeeServiceImpl getInstance(IEmployeeDao employeeDao) {
        if (instance == null) {
            instance = new EmployeeServiceImpl(employeeDao);
        }
        return instance;
    }

    private final IEmployeeDao employeeDao;

    @Override
    public void creerEmploye(Employee employee) {
        // Business logic can be added here
        employeeDao.save(employee);
    }

    @Override
    public void supprimerEmploye(int id) {
        employeeDao.delete(id);
    }

    @Override
    public Employee trouverEmploye(int id) {
        return employeeDao.findById(id);
    }

    @Override
    public List<Employee> trouverTousLesEmployes() {
        return employeeDao.findAll();
    }

    @Override
    public Employee dupliquerEmploye(int id) throws CloneNotSupportedException {
        Employee original = employeeDao.findById(id);
        if (original != null) {
            return (Employee) original.clone(); // Prototype Pattern
        }
        return null;
    }
}