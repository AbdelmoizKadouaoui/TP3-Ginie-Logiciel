package service;

import beans.Employee;
import dao.EmployeeDao;

public class EmployeeService {

    private static EmployeeService instance;
    private final EmployeeDao employeeDao = EmployeeDao.getInstance();

    private EmployeeService() {}

    public static EmployeeService getInstance() {
        if (instance == null) {
            instance = new EmployeeService();
        }
        return instance;
    }

    public Employee duplicateEmployee(int id, int newId) {
        try {
            return employeeDao.duplicate(id, newId);
        } catch (CloneNotSupportedException e) {
            System.err.println("Erreur lors de la duplication de l'employé : " + e.getMessage());
            return null;
        }
    }
}
