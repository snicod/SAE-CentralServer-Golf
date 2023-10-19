import org.bson.types.*;
import java.util.List;

public class Module {

    private ObjectId id;
    private String name;
    private String shortName;
    private String key;
    private String uc;
    private List<ObjectId> chipsets;

    public Module() {}

    public Module(String name, String shortName, String key, String uc, List<ObjectId> chipsets) {
        this.name = name;
        this.shortName = shortName;
        this.key = key;
        this.uc = uc;
        this.chipsets = chipsets;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public List<ObjectId> getChipsets() {
        return chipsets;
    }

    public void setChipsets(List<ObjectId> chipsets) {
        this.chipsets = chipsets;
    }

    public String toString() {
        return id+" "+name+" "+shortName+" "+key+" "+ uc+" "+ chipsets;
    }
}
