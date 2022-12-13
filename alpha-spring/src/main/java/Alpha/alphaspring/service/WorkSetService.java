package Alpha.alphaspring.service;

import Alpha.alphaspring.DTO.*;
import Alpha.alphaspring.domain.*;
import Alpha.alphaspring.repository.SubRoutineRepository;
import Alpha.alphaspring.repository.WorkOutRepository;
import Alpha.alphaspring.repository.WorkSetRepository;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Transactional
@Service
public class WorkSetService {

    @Autowired
    private WorkSetRepository workSetRepository;
    @Autowired
    private SubRoutineRepository subRoutineRepository;

    @Autowired
    private WorkOutRepository workOutRepository;

    public long join(WorkSetRegisterRequestDto request) throws Exception {
        SubRoutine subRoutine = subRoutineRepository.findById(request.getSubRoutineId()).orElseThrow(() -> new Exception("no such subroutine id"));
        WorkOut workOut = workOutRepository.findByName(request.getWorkOutName()).orElseThrow(() -> new Exception("no such workout name"));
        WorkSet workSet = request.toEntity(subRoutine, workOut);
        workSetRepository.save(workSet);
        return workSet.getId();
    }

    public List<WorkSetResponseDto> findWorkSetById(Long subRoutineId) throws Exception{
        List<WorkSet> workSets = workSetRepository.findBySubRoutine_Id(subRoutineId);

        List<WorkSetResponseDto> responseWorkSet = new ArrayList<>();
        Stream<WorkSet> stream = workSets.stream();
        stream.forEach(workSet -> {
            responseWorkSet.add(new WorkSetResponseDto().fromEntity(workSet));
        });
        return responseWorkSet;
    }
}
