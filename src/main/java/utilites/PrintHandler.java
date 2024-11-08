package utilites;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertThrows;

// Class PrintHandler<T>
// The generic class T of Class PrintHandler defines the type of value
// Author : IHyeon Hong(ihyeonhong0427@gmail.com)
public class PrintHandler<T> {
    private T value;
    private Boolean isPrintable;
    private Boolean isError;
    private String outputMethod;


    public PrintHandler(T value, Boolean isError, String outputMethod) {
        this.setValue(value);
        this.setIsError(isError);
        this.setOutputMethod(outputMethod);
    }


    public static void main(String[] args) {
        PrintHandler handler = new PrintHandler(1234, true, "print");
        System.out.println(handler.getValue());
        handler.printlnValue(handler.getValue(), true, "print");

    }

    public void printlnValue(T value, Boolean isError, String outputMethod) {
        String callMethod = null;
        try {
            if (isError) {
                switch (outputMethod) {
                    case "print":
                        callMethod = "java.lang.System.err.print";
                        Class clazz = Class.forName("java.lang.System");
                        Field field = clazz.getField("err");
                        Method method = java.lang.System.err.getClass().getMethod("print", String.class);
                        Object fieldInstance = field.get(null);
                        System.out.println(fieldInstance);
                        method.invoke(fieldInstance, "sibal");

//                        methodInvoker.invokeMethodByName("java.lang.System.err.print", ".*System");
                    case "println":
                        callMethod = "System.err.println";
                }
            } else {
                switch (outputMethod) {
                    case "print":
                        callMethod = "System.out.print";
                    case "println":
                        callMethod = "System.out.println";
                }
            }
        } catch (Exception e) {
        }
    }

    public void printValue(T value) {
        try {
            System.out.print(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean isPrintable() {
        try {
            assertThrows(RuntimeException.class, () -> {
                System.err.print(this.getValue());
            });
            assertThrows(RuntimeException.class, () -> {
                System.err.println(this.getValue());
            });
            assertThrows(RuntimeException.class, () -> {
                System.out.print(this.getValue());
            });
            assertThrows(RuntimeException.class, () -> {
                System.out.println(this.getValue());
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Boolean getPrintable() {
        return isPrintable;
    }

    public void setPrintable(Boolean printable) {
        isPrintable = printable;
    }

    public Boolean getIsError() {
        return isError;
    }

    public void setIsError(Boolean error) {
        isError = error;
    }

    public String getOutputMethod() {
        return outputMethod;
    }

    public void setOutputMethod(String outputMethod) {
        this.outputMethod = outputMethod;
    }

}
