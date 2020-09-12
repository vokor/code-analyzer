package analyzer;

public abstract class ElementCounter {

    protected int Counter;

    public int getCounter() {
        return Counter;
    }

    abstract void checkAndAdd(Object element);
}
