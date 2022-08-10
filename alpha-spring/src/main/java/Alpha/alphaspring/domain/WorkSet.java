package Alpha.alphaspring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class WorkSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sub_routine_id")
    private SubRoutine subRoutine;

    @ManyToOne
    @JoinColumn(name = "work_out_id")
    private WorkOut workOut;
}


