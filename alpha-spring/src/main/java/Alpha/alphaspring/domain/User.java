package Alpha.alphaspring.domain;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Getter
@Setter
@Entity
//@RequiredArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column
    private String pw;
    @Column
    private String name;
    @Column
    private String gender;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column
    private Long age;

//    public String getName() {
//        return name;
//    }
}
