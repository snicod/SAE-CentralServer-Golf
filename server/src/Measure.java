import org.bson.types.*;

import java.time.LocalDateTime;
import java.util.List;

public class Measure {

    private ObjectId id;
    private String type;
    private LocalDateTime date;
    private String value;
    private ObjectId module;

    public Measure() {}

    public Measure(String type, LocalDateTime date, String value, ObjectId module) {
        this.type = type;
        this.date = date;
        this.value = value;
        this.module = module;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ObjectId getModule() {
        return module;
    }

    public void setModule(ObjectId module) {
        this.module = module;
    }

    public String toString() {
        return id+" "+type+" "+date+" "+value+" "+module;
    }
}
