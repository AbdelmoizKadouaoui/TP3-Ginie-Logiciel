package dao;

public interface IConnection {
    void insert(Object o);
    void delete(int id);
    void update(Object o);
    Object select(int id);
}