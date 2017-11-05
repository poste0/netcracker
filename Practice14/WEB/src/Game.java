import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "Game")
@SessionScoped
public class Game implements Serializable {
    private static final long serialVersionUID=1234567L;
    private int min,max;
    private int current;
    private static final int MORE=0,LESS=1,EQUAL=2;
    private final String url="second.xhtml";
    private final String honest="honest.xhtml";
    private final String wrong="wrong.xhtml";
    public Game(){}
    public String  init(){
        current=(max+min)/2;
        return url;
    }
    public int getMORE(){return MORE;}
    public int getLESS(){return LESS;}
    public int getEQUAL(){return EQUAL;}

    public String guess(int param) throws Exception {

        switch(param){
            case MORE:
                if(max==current||min==current) return wrong;
                min=current;
                current=(max+min)/2;
                return url;
            case LESS:
                if(max==current||min==current) return wrong;
                max=current;
                current=(max+min)/2;
                return url;
            case EQUAL:
                return honest;
            default:
                throw new Exception("Wrong operation");
        }
    }
    public int getCurrent(){return current;}
    public void setCurrent(int current){this.current=current;}
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
