package Alpha.alphaspring.service;

import Alpha.alphaspring.DTO.UserDetails;
import Alpha.alphaspring.domain.User;
import Alpha.alphaspring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl {

    @Autowired
    private final UserRepository userRepository;

    public UserDetails findUser(String userId, String provider) throws Exception {
        User user = userRepository.findByUserIdAndProvider(userId, provider).orElseThrow(() -> new Exception("ohlcv result set null"));
        return UserDetails.builder()
                .age(user.getAge())
                .gender(user.getGender())
                .name(user.getName())
                .id(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .userId(user.getUserId())
                .pw(user.getPw()).build();
    }
}