package Alpha.alphaspring.service;

import Alpha.alphaspring.DTO.UserDetails;
import Alpha.alphaspring.domain.User;
import Alpha.alphaspring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    public UserDetails findUser(String userId, String provider) throws UsernameNotFoundException {
        User user = userRepository
                .findByUsernameAndProvider(userId, provider)
                .orElseThrow(() -> new UsernameNotFoundException("cannot find such user"));

        return UserDetails.builder()
                .build();
    }


}
