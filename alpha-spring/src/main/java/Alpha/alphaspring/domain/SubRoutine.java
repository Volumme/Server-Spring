package Alpha.alphaspring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class SubRoutine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @Column(name = "name")
    private String name;

//    @OneToMany(mappedBy = "subRoutine")
//    List<WorkSet> workSets = new ArrayList<>();
}

