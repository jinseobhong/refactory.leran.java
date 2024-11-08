package variable;

public class Assign<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void printlnValue(T value) {
        System.out.println(value);
    }
}
