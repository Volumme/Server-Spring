package Alpha.alphaspring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class SubSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "work_set_id")
    private WorkSet workSet;

    @Column(name = "set_no")
    private Long setNo;

    @Column(name = "count")
    private Long count;

    @Column(name = "weight")
    private Long weight;
}
