package Alpha.alphaspring.service;

import Alpha.alphaspring.DTO.SubRoutineRegisterRequestDto;
import Alpha.alphaspring.DTO.SubRoutineResponseDto;
import Alpha.alphaspring.DTO.UserDetails;
import Alpha.alphaspring.Utils.CommonTokenUtils;
import Alpha.alphaspring.Utils.KakaoTokenUtils;
import Alpha.alphaspring.domain.Routine;
import Alpha.alphaspring.domain.SubRoutine;
import Alpha.alphaspring.domain.User;
import Alpha.alphaspring.repository.RoutineRepository;
import Alpha.alphaspring.repository.SubRoutineRepository;
import Alpha.alphaspring.repository.UserRepository;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Transactional
@Service
public class SubRoutineService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubRoutineRepository subRoutineRepository;
    @Autowired
    private RoutineRepository routineRepository;
    @Autowired
    private CommonTokenUtils tokenUtils;
    @Autowired
    private KakaoTokenUtils kakaoTokenUtils;

    public List<SubRoutineResponseDto> findSubRoutines() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        String provider = userDetails.getProvider();
        User user = userRepository.findByUsernameAndProvider(username, provider).orElseThrow(() -> new Exception("No user found"));
        List<Routine> routines = routineRepository.findByUser(user);

        List<SubRoutine> subRoutines = new ArrayList<>();
        Stream<Routine> streamRoutines = routines.stream();
        streamRoutines.forEach(routine ->{
            subRoutines.addAll(subRoutineRepository.findByRoutine(routine));
                }
        );

        List<SubRoutineResponseDto> responseSubRoutine = new ArrayList<>();
        Stream<SubRoutine> stream = subRoutines.stream();
        stream.forEach(subRoutine -> {
            responseSubRoutine.add(new SubRoutineResponseDto().fromEntity(subRoutine));
        });
        return responseSubRoutine;
    }

    public void join(SubRoutineRegisterRequestDto request) throws ParseException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsernameAndProvider(userDetails.getUsername(), userDetails.getProvider()).orElseThrow(() -> new RuntimeException("can not find user!"));
        Routine routine = routineRepository.findByNameAndUser(request.getRoutineName(), user).orElseThrow(() -> new UsernameNotFoundException("cannot find such user"));
        SubRoutine subRoutine = request.toEntity(routine);
        subRoutineRepository.save(subRoutine);
    }
}
