package Alpha.alphaspring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class WorkOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "machine_name")
    private String machineName;

    @Column(name = "body_part")
    private String bodyPart;

//    @OneToMany(mappedBy = "workOut")
//    List<WorkSet> workSets = new ArrayList<>();
}

