package exception;

public class BuildingUnderArrestException extends Exception {
    public BuildingUnderArrestException(String s){
        super(s);
    }
    public BuildingUnderArrestException(){
        super();
    }
    @Override
    public String toString(){
        return super.toString();
    }
}
