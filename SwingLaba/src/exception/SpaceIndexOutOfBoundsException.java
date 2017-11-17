package exception;

public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public SpaceIndexOutOfBoundsException(String s){
        super(s);

    }
    public SpaceIndexOutOfBoundsException() {
        super();
    }
    @Override
    public String toString(){
        return super.toString();
    }
}
