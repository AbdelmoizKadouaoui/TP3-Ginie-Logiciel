package dao.strategy;

import beans.Employee;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileSavingStrategy implements ISavingStrategy {
    @Override
    public void save(List<Employee> employees) {
        System.out.println("Stratégie: Sauvegarde de " + employees.size() + " employés dans le fichier 'employees.txt'.");
        try (PrintWriter out = new PrintWriter(new FileWriter("employees.txt"))) {
            employees.forEach(e -> out.println(e.getNom()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}