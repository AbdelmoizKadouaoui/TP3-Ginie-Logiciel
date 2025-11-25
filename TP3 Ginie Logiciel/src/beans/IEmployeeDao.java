package dao;

import beans.Employee;
import java.util.List;

public interface IEmployeeDao {
    void save(Employee employee);
    void delete(int id);
    Employee findById(int id);
    List<Employee> findAll();
}