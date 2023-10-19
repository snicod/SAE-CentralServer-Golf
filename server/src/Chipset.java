import org.bson.types.ObjectId;

import java.util.List;

public class Chipset {

    private ObjectId id;
    private String name;
    private String description;
    private List<String> links;
    private List<String> caps;

    public Chipset() {}

    public Chipset(String name, String description, List<String> links, List<String> caps) {
        this.name = name;
        this.description = description;
        this.links = links;
        this.caps = caps;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public List<String> getCaps() {
        return caps;
    }

    public void setCaps(List<String> caps) {
        this.caps = caps;
    }

    public String toString() {
        return id+" "+name+" "+caps;
    }
}
