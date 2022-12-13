package Alpha.alphaspring.controller;

import Alpha.alphaspring.DTO.*;
import Alpha.alphaspring.service.SubSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubSetController {
    @Autowired
    private SubSetService subSetService;

    @PostMapping("/subSet")
    public IdResponseDto registerSubSet(
            @RequestBody SubSetRegisterRequestDto requestBody
            ) throws Exception{
        long id = subSetService.join(requestBody);
        return new IdResponseDto(id);
    }

    @GetMapping("/subSets")
    public List<SubSetResponseDto> findSubSetById(@RequestParam(value = "workSetId") Long workSetId){
        try {
            return subSetService.findSubSetById(workSetId);
        }
        catch (Exception e){
            System.out.println("e = " + e);
            return null;
        }
    }
}
