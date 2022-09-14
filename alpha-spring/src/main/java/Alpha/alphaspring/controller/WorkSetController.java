package Alpha.alphaspring.controller;

import Alpha.alphaspring.DTO.StringResponseDto;
import Alpha.alphaspring.DTO.SubRoutineResponseDto;
import Alpha.alphaspring.DTO.WorkSetRegisterRequestDto;
import Alpha.alphaspring.DTO.WorkSetResponseDto;
import Alpha.alphaspring.service.WorkSetService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkSetController {
    @Autowired
    private WorkSetService workSetService;

    @PostMapping("/workSet")
    public StringResponseDto registerWorkSet(
            @RequestBody WorkSetRegisterRequestDto requestBody
    ) throws Exception {
        workSetService.join(requestBody);
        return new StringResponseDto("WorkSet registered");
    }

    @GetMapping("/workSets")
    public List<WorkSetResponseDto> findSubroutinesById(@RequestParam(value = "subRoutineId") Long subRoutineId){
        try {
            return workSetService.findWorkSetById(subRoutineId);
        }
        catch (Exception e){
            System.out.println("e = " + e);
            return null;
        }
    }

}
