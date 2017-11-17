package exception;

public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public FloorIndexOutOfBoundsException(String s){
        super(s);
    }
    public FloorIndexOutOfBoundsException(){
        super();
    }
    @Override
    public String toString(){
        return super.toString();
    }
}
