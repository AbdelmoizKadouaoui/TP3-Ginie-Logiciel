package dao.visitor;

import beans.Employee;

public interface IVisitor {
    void visit(Employee employee);
}