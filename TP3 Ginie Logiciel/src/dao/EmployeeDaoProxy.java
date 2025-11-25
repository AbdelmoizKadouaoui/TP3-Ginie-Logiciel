package dao;

import beans.Employee;
import security.SecurityContext;
import security.User;

import java.util.List;

public class EmployeeDaoProxy implements IEmployeeDao {

    private final IEmployeeDao target;

    public EmployeeDaoProxy(IEmployeeDao target) {
        this.target = target;
    }

    private boolean isGranted() {
        User user = SecurityContext.getCurrentUser();
        return user != null && user.isGranted();
    }

    @Override
    public void insert(Employee e) {
        if (isGranted()) {
            target.insert(e);
        } else {
            System.out.println("Accès refusé : l'utilisateur courant n'a pas le droit d'insérer.");
        }
    }

    @Override
    public void update(Employee e) {
        if (isGranted()) {
            target.update(e);
        } else {
            System.out.println("Accès refusé : l'utilisateur courant n'a pas le droit de modifier.");
        }
    }

    @Override
    public void delete(int id) {
        if (isGranted()) {
            target.delete(id);
        } else {
            System.out.println("Accès refusé : l'utilisateur courant n'a pas le droit de supprimer.");
        }
    }

    @Override
    public Employee select(int id) {
        return target.select(id);
    }

    @Override
    public List<Employee> getAll() {
        return target.getAll();
    }

    @Override
    public Employee duplicate(int id, int newId) throws CloneNotSupportedException {
        if (isGranted()) {
            return target.duplicate(id, newId);
        } else {
            System.out.println("Accès refusé : l'utilisateur courant n'a pas le droit de dupliquer.");
            return null;
        }
    }
}
