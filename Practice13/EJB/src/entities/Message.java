package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Time;

@Entity(name = "Message")
@Table(name = "msg")
public class Message implements Serializable {
    private static long serialVersionUID=29102017L;
    @Column(name = "message")
    private String message;
    @Id
    @Column(name = "time")
    private Time time;
    public Message(String message, Time time){
        this.message=message;
        this.time=time;

    }
    public Message(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
