package Alpha.alphaspring.controller;

import Alpha.alphaspring.DTO.*;
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
    public IdResponseDto registerWorkSet(
            @RequestBody WorkSetRegisterRequestDto requestBody
    ) throws Exception {
        long id = workSetService.join(requestBody);
        return new IdResponseDto(id);
    }

    @GetMapping("/workSets")
    public List<WorkSetResponseDto> findWorkSetById(@RequestParam(value = "subRoutineId") Long subRoutineId){
        try {
            return workSetService.findWorkSetById(subRoutineId);
        }
        catch (Exception e){
            System.out.println("e = " + e);
            return null;
        }
    }

}
