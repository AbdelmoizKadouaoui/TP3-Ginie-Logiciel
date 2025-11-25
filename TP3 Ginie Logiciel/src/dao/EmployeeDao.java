package dao;

import beans.Employee;
import dao.strategy.ISavingStrategy;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements IEmployeeDao {

    private static EmployeeDao instance;
    private final IConnection connection;
    private final List<Employee> employees = new ArrayList<>();
    
    // Observer Pattern: list of observers
    private final List<IObserver> observers = new ArrayList<>();

    // Strategy Pattern: saving strategy
    private ISavingStrategy savingStrategy;

    private EmployeeDao(ISavingStrategy strategy) {
        connection = ConnectionFactory.getConnection("mysql");
        this.savingStrategy = strategy;
    }

    public static EmployeeDao getInstance(ISavingStrategy strategy) {
        if (instance == null) {
            instance = new EmployeeDao(strategy);
        }
        return instance;
    }

    @Override
    public void insert(Employee e) {
        employees.add(e);
        connection.insert(e);
        notifyObservers();
    }

    @Override
    public void update(Employee e) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == e.getId()) {
                employees.set(i, e);
                connection.update(e);
                notifyObservers();
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        employees.removeIf(emp -> emp.getId() == id);
        connection.delete(id);
        notifyObservers();
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

    // --- Observer Pattern Methods ---
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        observers.forEach(IObserver::update);
    }

    public void saveAllData() {
        savingStrategy.save(employees);
    }
}
