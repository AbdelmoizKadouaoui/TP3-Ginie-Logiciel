package dao.proxy;

import beans.Employee;
import dao.IEmployeeDao;

import java.util.List;

// Proxy Pattern
public class EmployeeDaoProxy implements IEmployeeDao {

    private final IEmployeeDao realDao;
    private final UserContext userContext;

    public EmployeeDaoProxy(IEmployeeDao realDao) {
        this.realDao = realDao;
        this.userContext = new UserContext(); // In a real app, this would be injected.
    }

    private boolean isGranted() {
        // Simplified permission check
        return "admin".equals(userContext.getRole());
    }

    @Override
    public void save(Employee employee) {
        if (isGranted()) {
            realDao.save(employee);
        } else {
            throw new SecurityException("Accès refusé : droits de mise à jour insuffisants.");
        }
    }

    @Override
    public void delete(int id) {
        if (isGranted()) {
            realDao.delete(id);
        } else {
            throw new SecurityException("Accès refusé : droits de mise à jour insuffisants.");
        }
    }

    // Read operations are not protected by this proxy
    @Override
    public Employee findById(int id) { return realDao.findById(id); }
    @Override
    public List<Employee> findAll() { return realDao.findAll(); }

    // Mock user context for demonstration
    private static class UserContext {
        public String getRole() { return "admin"; /* or "guest" to test failure */ }
    }
}
