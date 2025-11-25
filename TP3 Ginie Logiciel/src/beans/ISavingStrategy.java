package dao.strategy;

import beans.Employee;
import java.util.List;

public interface ISavingStrategy {
    void save(List<Employee> employees);
}