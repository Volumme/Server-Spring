package Alpha.alphaspring.DTO;


import Alpha.alphaspring.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@Builder
public class UserDetails {

        private Long id;

        private String userId;

        private String pw;

        private String name;

        private String gender;

        private String phoneNumber;

        private Long age;

        private Collection<? extends GrantedAuthority> authorities;

        private boolean isLocked;

        private boolean isEnabled;

        private boolean isExpired;

        public static UserDetails of(User user) {
                ModelMapper modelMapper = new ModelMapper();
                UserDetails userDetails = modelMapper.map(user, UserDetails.class);
                userDetails.setLocked(false);
                userDetails.setExpired(false);
                userDetails.setEnabled(true);
                return userDetails;
        }
}
