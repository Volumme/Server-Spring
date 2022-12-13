package Alpha.alphaspring.service;

import Alpha.alphaspring.DTO.SubSetRegisterRequestDto;
import Alpha.alphaspring.DTO.SubSetResponseDto;
import Alpha.alphaspring.DTO.WorkSetRegisterRequestDto;
import Alpha.alphaspring.DTO.WorkSetResponseDto;
import Alpha.alphaspring.domain.SubRoutine;
import Alpha.alphaspring.domain.SubSet;
import Alpha.alphaspring.domain.WorkOut;
import Alpha.alphaspring.domain.WorkSet;
import Alpha.alphaspring.repository.SubSetRepository;
import Alpha.alphaspring.repository.WorkSetRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Transactional
@Service
public class SubSetService {

    @Autowired
    private SubSetRepository subSetRepository;

    @Autowired
    private WorkSetRepository workSetRepository;

    public long join(SubSetRegisterRequestDto request) throws Exception {
        WorkSet workSet = workSetRepository.findById(request.getWorkSetId()).orElseThrow(() -> new Exception("no such workset id"));
        SubSet subSet = request.toEntity(workSet);
        subSetRepository.save(subSet);
        return subSet.getId();
    }

    public List<SubSetResponseDto> findSubSetById(Long workSetId) throws Exception{
        List<SubSet> subSets = subSetRepository.findByWorkSet_Id(workSetId);

        List<SubSetResponseDto> responseSubSet = new ArrayList<>();
        Stream<SubSet> stream = subSets.stream();
        stream.forEach(subSet -> {
            responseSubSet.add(new SubSetResponseDto().fromEntity(subSet));
        });
        return responseSubSet;
    }
}
