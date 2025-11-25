package dao.visitor;

import beans.Employee;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TxtExportVisitor implements IVisitor {
    private final StringBuilder sb = new StringBuilder();

    @Override
    public void visit(Employee employee) {
        String line = String.format("ID=%d, Name=%s, Poste=%s", employee.getId(), employee.getNom(), employee.getPoste());
        sb.append(line).append(System.lineSeparator());
    }

    public void exportToFile(String filename) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            out.print(sb.toString());
        }
        System.out.println("Données exportées vers " + filename);
    }
}