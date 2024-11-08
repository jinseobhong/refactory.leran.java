package variable;

import java.awt.print.Printable;

public class Declaration<T> {
    // Declaration of Variables
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
