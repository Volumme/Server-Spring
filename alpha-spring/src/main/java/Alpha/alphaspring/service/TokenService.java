package Alpha.alphaspring.service;

import Alpha.alphaspring.DTO.TokenRefreshRequestDto;
import Alpha.alphaspring.DTO.TokenRefreshResponseDto;
import Alpha.alphaspring.DTO.UserDetails;
import Alpha.alphaspring.Utils.JwtTokenUtils;
import Alpha.alphaspring.domain.User;
import Alpha.alphaspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TokenService {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public TokenRefreshResponseDto refreshToken(TokenRefreshRequestDto requestBody){

        try{
            User user = userRepository.findByRefreshToken(requestBody.getRefreshToken()).orElseThrow(() -> new Exception("no refreshToken exists"));
            if (!jwtTokenUtils.validate(requestBody.getRefreshToken())) return null;
            String accessToken = jwtTokenUtils.generateAccessToken(user.getUsername(), user.getProvider());
            String refreshToken = jwtTokenUtils.generateRefreshToken();
            userService.saveToken(user.getUsername(), user.getProvider(), refreshToken);

            return TokenRefreshResponseDto.builder()
                    .refreshToken(refreshToken)
                    .accessToken(accessToken)
                    .build()
                    ;
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return null;
    }
}
