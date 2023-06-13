package pojo;

import lombok.Data;
import java.util.List;

@Data
public class Character {
    private Long id;
    private String created;
    private List<String> episode;
    private String gender;
    private String image;
    private Location location;
    private String name;
    private Origin origin;
    private String species;
    private String status;
    private String type;
    private String url;
}
