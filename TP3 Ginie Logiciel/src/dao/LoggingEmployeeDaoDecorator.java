package dao;

import beans.Employee;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

public class LoggingEmployeeDaoDecorator implements IEmployeeDao {

    private final IEmployeeDao delegate;
    private final PrintWriter log;

    public LoggingEmployeeDaoDecorator(IEmployeeDao delegate) {
        this.delegate = delegate;
        try {
            this.log = new PrintWriter(new FileWriter("db.log", true));
        } catch (IOException e) {
            throw new RuntimeException("Impossible d'ouvrir le fichier db.log", e);
        }
    }

    private void writeLog(String message) {
        log.println(LocalDateTime.now() + " - " + message);
        log.flush();
    }

    @Override
    public void insert(Employee e) {
        delegate.insert(e);
        writeLog("Insertion de l'employé id=" + e.getId());
    }

    @Override
    public void update(Employee e) {
        delegate.update(e);
        writeLog("Mise à jour de l'employé id=" + e.getId());
    }

    @Override
    public void delete(int id) {
        delegate.delete(id);
        writeLog("Suppression de l'employé id=" + id);
    }

    @Override
    public Employee select(int id) {
        Employee result = delegate.select(id);
        writeLog("Recherche de l'employé id=" + id);
        return result;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> list = delegate.getAll();
        writeLog("Listing de tous les employés (" + list.size() + " trouvés)");
        return list;
    }

    @Override
    public Employee duplicate(int id, int newId) throws CloneNotSupportedException {
        Employee dup = delegate.duplicate(id, newId);
        writeLog("Duplication de l'employé id=" + id + " vers newId=" + newId);
        return dup;
    }
}
