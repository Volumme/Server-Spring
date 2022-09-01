package Alpha.alphaspring.service;

import Alpha.alphaspring.DTO.RoutineRegisterRequestDto;
import Alpha.alphaspring.DTO.RoutineResponseDto;
import Alpha.alphaspring.DTO.UserDetails;
import Alpha.alphaspring.Utils.CommonTokenUtils;
import Alpha.alphaspring.Utils.KakaoTokenUtils;
import Alpha.alphaspring.domain.Routine;
import Alpha.alphaspring.domain.User;
import Alpha.alphaspring.repository.RoutineRepository;
import Alpha.alphaspring.repository.UserRepository;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Stream;

@Transactional
@Service
public class RoutineService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoutineRepository routineRepository;
    @Autowired
    private CommonTokenUtils tokenUtils;
    @Autowired
    private KakaoTokenUtils kakaoTokenUtils;

    public List<RoutineResponseDto> findRoutines() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        String provider = userDetails.getProvider();
        User user = userRepository.findByUsernameAndProvider(username, provider).orElseThrow(() -> new Exception("No user found"));
        List<Routine> routines = routineRepository.findByUser(user);

        List<RoutineResponseDto> responseRoutine = new ArrayList<>();
        Stream<Routine> stream = routines.stream();
        stream.forEach(routine -> {
            responseRoutine.add(new RoutineResponseDto().fromEntity(routine));
        });
        return responseRoutine;
    }

    public void join(RoutineRegisterRequestDto request) throws ParseException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsernameAndProvider(userDetails.getUsername(), userDetails.getProvider()).orElseThrow(() -> new RuntimeException("can not find user!"));
        Routine routine = request.toEntity(user);
        routineRepository.save(routine);
    }

}

