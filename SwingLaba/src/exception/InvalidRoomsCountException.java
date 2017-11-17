package exception;

public class InvalidRoomsCountException extends IllegalArgumentException {
    public InvalidRoomsCountException(String s){
        super(s);
    }
    public InvalidRoomsCountException() {

    }
    @Override
    public String toString(){
        return super.toString();
    }
}
