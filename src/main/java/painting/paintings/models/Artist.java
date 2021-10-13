package painting.paintings.models;

import com.sun.istack.Nullable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "artists")
@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @ApiModelProperty(notes = "Name of the artist")
    private String name;

    @Column
    private int age;

    @Column
    private String primaryStyle;

    @Column(length = 100)
    private String nationality;

    @Column
    private Date birthDate;

    @Enumerated(value = EnumType.STRING)
    @Column
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "gallery_id")
    @Nullable
    private Gallery gallery;

}
