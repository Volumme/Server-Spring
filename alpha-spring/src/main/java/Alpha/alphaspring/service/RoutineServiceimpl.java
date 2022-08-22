package Alpha.alphaspring.service;

import Alpha.alphaspring.DTO.RoutineRegisterRequestDto;
import Alpha.alphaspring.DTO.RoutineResponseDto;
import Alpha.alphaspring.Utils.CommonTokenUtils;
import Alpha.alphaspring.Utils.KakaoTokenUtils;
import Alpha.alphaspring.domain.Routine;
import Alpha.alphaspring.domain.User;
import Alpha.alphaspring.repository.RoutineRepository;
import Alpha.alphaspring.repository.UserRepository;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Stream;

@Transactional
@Service
public class RoutineServiceimpl {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoutineRepository routineRepository;
    @Autowired
    private CommonTokenUtils tokenUtils;
    @Autowired
    private KakaoTokenUtils kakaoTokenUtils;

    public List<RoutineResponseDto> findRoutines() throws Exception {
        List<Routine> routines = routineRepository.findAll();
        List<RoutineResponseDto> responseRoutine = new ArrayList<>();
        Stream<Routine> stream = routines.stream();
        stream.forEach(routine -> {
            responseRoutine.add(new RoutineResponseDto().fromEntity(routine));
        });
        return responseRoutine;
    }

    public void join(Map<String, Object> headers, RoutineRegisterRequestDto request) throws ParseException {
        String authorizationHeader = (String) headers.get("authorization");
        String[] bearerHeader = authorizationHeader.split(" ");
        String jwtToken = bearerHeader[1];

        String subject = tokenUtils.getSubject(jwtToken);
        String provider = tokenUtils.getIssuer(jwtToken);

        Map<String, Object> args = new HashMap<>();
        args.put("provider", provider);
        args.put("username", subject);

        User user = userRepository
                .findByUsernameAndProvider(
                        (String) args.get("username"),
                        (String) args.get("provider"))
                .orElseThrow(() -> new UsernameNotFoundException("cannot find such user"));
        Routine routine = request.toEntity(user);

        routineRepository.save(routine);
    }
}

