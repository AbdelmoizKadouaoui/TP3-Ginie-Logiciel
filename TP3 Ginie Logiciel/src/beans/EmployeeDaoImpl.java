package dao;

import beans.Employee;
import dao.connection.ConnectionFactory;
import dao.connection.IConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements IEmployeeDao {

    // Singleton Pattern
    private static EmployeeDaoImpl instance;

    private EmployeeDaoImpl() {
        // Private constructor for Singleton
    }

    public static synchronized EmployeeDaoImpl getInstance() {
        if (instance == null) {
            instance = new EmployeeDaoImpl();
        }
        return instance;
    }

    // Using Factory to get connection
    private final IConnection dbConnection = ConnectionFactory.getConnection();

    // In-memory database for simulation
    private final Map<Integer, Employee> database = new HashMap<>();

    @Override
    public void save(Employee employee) {
        System.out.println("DAO: Saving employee...");
        database.put(employee.getId(), employee);
        dbConnection.insert("INSERT INTO employees...");
    }

    @Override
    public void delete(int id) {
        System.out.println("DAO: Deleting employee...");
        database.remove(id);
        dbConnection.delete("DELETE FROM employees...");
    }

    @Override
    public Employee findById(int id) {
        return database.get(id);
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(database.values());
    }
}