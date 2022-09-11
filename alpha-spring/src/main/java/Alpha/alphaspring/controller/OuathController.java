package Alpha.alphaspring.controller;

import Alpha.alphaspring.DTO.TokenRefreshRequestDto;
import Alpha.alphaspring.DTO.TokenRefreshResponseDto;
import Alpha.alphaspring.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OuathController {
    @Autowired
    private TokenService tokenService;

    @PostMapping("/refresh")
    public TokenRefreshResponseDto refreshToken(
            @RequestBody TokenRefreshRequestDto requestBody
    ) throws Exception {
        return tokenService.refreshToken(requestBody);
    }

}
