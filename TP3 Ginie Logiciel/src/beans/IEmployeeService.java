package service;

import beans.Employee;
import java.util.List;

public interface IEmployeeService {
    void creerEmploye(Employee employee);
    void supprimerEmploye(int id);
    Employee trouverEmploye(int id);
    List<Employee> trouverTousLesEmployes();
    Employee dupliquerEmploye(int id) throws CloneNotSupportedException;
}
