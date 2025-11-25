package dao;

public class MySqlConnection implements IConnection {

    @Override
    public void insert(Object o) {
        System.out.println("[MySQL] Insertion : " + o);
    }

    @Override
    public void delete(int id) {
        System.out.println("[MySQL] Suppression de l'employé id=" + id);
    }

    @Override
    public Object select(int id) {
        System.out.println("[MySQL] Sélection de l'employé id=" + id);
        return null;
    }
    @Override
    public void update (Object o) {
        System.out.println("[MySQL] modifier de l'employé id=" + o);
    }
}
