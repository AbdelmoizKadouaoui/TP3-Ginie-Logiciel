package beans.memento;

import java.util.ArrayList;
import java.util.List;

public class History {
    private final List<EmployeeMemento> mementos = new ArrayList<>();

    public void push(EmployeeMemento memento) {
        mementos.add(memento);
    }

    public EmployeeMemento pop() {
        if (mementos.isEmpty()) {
            return null;
        }
        int lastIndex = mementos.size() - 1;
        return mementos.remove(lastIndex);
    }
}