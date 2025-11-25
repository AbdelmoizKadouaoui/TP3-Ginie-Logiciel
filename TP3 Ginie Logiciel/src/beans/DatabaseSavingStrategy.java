package dao.strategy;

import beans.Employee;
import java.util.List;

public class DatabaseSavingStrategy implements ISavingStrategy {
    @Override
    public void save(List<Employee> employees) {
        // En réalité, on bouclerait sur les employés pour les insérer/mettre à jour
        // en base de données.
        System.out.println("Stratégie: Sauvegarde de " + employees.size() + " employés dans la base de données.");
    }
}