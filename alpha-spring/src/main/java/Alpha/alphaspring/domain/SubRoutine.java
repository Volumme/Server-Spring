package Alpha.alphaspring.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

