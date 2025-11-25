package dao.decorators;

import beans.Employee;
import dao.IEmployeeDao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

// Decorator Pattern
public class LoggingEmployeeDaoDecorator implements IEmployeeDao {

    private final IEmployeeDao wrappedDao;

    public LoggingEmployeeDaoDecorator(IEmployeeDao wrappedDao) {
        this.wrappedDao = wrappedDao;
    }

    private void log(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter("db.log", true))) {
            out.println(new Date() + " : " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Employee employee) {
        log("Insertion/Mise à jour de l'employé ID: " + employee.getId());
        wrappedDao.save(employee);
    }

    @Override
    public void delete(int id) {
        log("Suppression de l'employé ID: " + id);
        wrappedDao.delete(id);
    }

    @Override
    public Employee findById(int id) {
        log("Recherche de l'employé ID: " + id);
        return wrappedDao.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        log("Listing de tous les employés.");
        return wrappedDao.findAll();
    }
}
