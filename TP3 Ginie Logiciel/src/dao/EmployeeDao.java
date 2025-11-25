package dao;

import beans.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements IEmployeeDao {

    private static EmployeeDao instance;
    private final IConnection connection;
    private final List<Employee> employees = new ArrayList<>();

    private EmployeeDao() {
        connection = ConnectionFactory.getConnection("mysql");
    }

    public static EmployeeDao getInstance() {
        if (instance == null) {
            instance = new EmployeeDao();
        }
        return instance;
    }

    @Override
    public void insert(Employee e) {
        employees.add(e);
        connection.insert(e);
    }

    @Override
    public void update(Employee e) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == e.getId()) {
                employees.set(i, e);
                connection.update(e);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        employees.removeIf(emp -> emp.getId() == id);
        connection.delete(id);
    }

    @Override
    public Employee select(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) return e;
        }
        connection.select(id);
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return employees;
    }

    @Override
    public Employee duplicate(int id, int newId) throws CloneNotSupportedException {
        Employee original = select(id);
        if (original != null) {
            Employee cloned = (Employee) original.clone();
            cloned.setId(newId);
            insert(cloned);
            return cloned;
        }
        return null;
    }
}
