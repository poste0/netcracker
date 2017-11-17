package exception;

public class InvalidSpaceAreaException extends IllegalArgumentException {
    public InvalidSpaceAreaException() {
    }

    public InvalidSpaceAreaException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
