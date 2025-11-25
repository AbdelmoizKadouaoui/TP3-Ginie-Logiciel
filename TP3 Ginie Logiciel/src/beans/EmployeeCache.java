package dao;

import beans.Employee;

import java.util.List;

public class EmployeeCache implements IObserver {

    private final IEmployeeDao dao;
    private List<Employee> cachedEmployees;

    public EmployeeCache(IEmployeeDao dao) {
        this.dao = dao;
        // S'enregistrer comme observateur
        if (dao instanceof EmployeeDao) {
            ((EmployeeDao) dao).addObserver(this);
        }
        // Charger le cache initial
        update();
    }

    @Override
    public void update() {
        System.out.println("[Cache] Mise à jour du cache...");
        this.cachedEmployees = dao.getAll();
    }

    public List<Employee> getEmployees() {
        return cachedEmployees;
    }
}