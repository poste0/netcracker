package beans;

import java.time.LocalTime;

public class Message {
    private String message;
    private LocalTime time;
    public Message(String message,LocalTime time){
        this.message=message;
        this.time=time;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
