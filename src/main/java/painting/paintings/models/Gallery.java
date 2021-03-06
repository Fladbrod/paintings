package painting.paintings.models;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "galleries")
public class Gallery {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String owner;

    @Column
    private String location;

    @Column
    private int squareFeet;

    @Column
    private String image;

    // Lazy henter ikke alle artists, men kun dem man har brug for, FetchType.EAGER henter alle relationer ud
    // CascadeType
    //JsonIgnore gør at der ikke kommer infinitive recursion i f.eks postman, hvor den bliver ved med at spytte data ud på get request.
    //
    @JsonIgnore
    @OneToMany(mappedBy = "gallery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Artist> artists;

}
