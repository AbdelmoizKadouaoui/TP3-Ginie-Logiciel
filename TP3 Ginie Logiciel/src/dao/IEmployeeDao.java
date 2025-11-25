package dao;

import beans.Employee;
import java.util.List;

public interface IEmployeeDao {
    void insert(Employee e);
    void update(Employee e);
    void delete(int id);
    Employee select(int id);
    List<Employee> getAll();
    Employee duplicate(int id, int newId) throws CloneNotSupportedException;
}
