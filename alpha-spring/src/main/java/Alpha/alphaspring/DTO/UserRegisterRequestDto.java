package Alpha.alphaspring.DTO;

import Alpha.alphaspring.domain.User;

import java.util.Map;

public class UserRegisterRequestDto implements IRequestDto<User>{
    private String nickName;

    private boolean isNewbie;

    private String gender;

    private Float height;

    private Float weight;

    private Float bodyFatPer;

    private Float musclePer;

    @Override
    public User toEntity(Map<String, Object> args) {
        return User.builder()
                .provider((String) args.get("provider"))
                .username((String) args.get("username"))
                .gender(gender)
                .height(height)
                .weight(weight)
                .bodyFatPer(bodyFatPer)
                .musclePer(musclePer)
                .nickname(nickName)
                .isNewbie(isNewbie)
                .build();
    }
}
/*
닉네임
경력
성별
키
몸무게
체지방율
근육량
 */