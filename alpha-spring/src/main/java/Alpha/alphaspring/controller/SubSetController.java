package Alpha.alphaspring.controller;

import Alpha.alphaspring.DTO.StringResponseDto;
import Alpha.alphaspring.DTO.SubSetRegisterRequestDto;
import Alpha.alphaspring.DTO.SubSetResponseDto;
import Alpha.alphaspring.DTO.WorkSetResponseDto;
import Alpha.alphaspring.service.SubSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubSetController {
    @Autowired
    private SubSetService subSetService;

    @PostMapping("/subSet")
    public StringResponseDto registerSubSet(
            @RequestBody SubSetRegisterRequestDto requestBody
            ) throws Exception{
        subSetService.join(requestBody);
        return new StringResponseDto("SubSet registered");
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
