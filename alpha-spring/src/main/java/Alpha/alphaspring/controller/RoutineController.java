package Alpha.alphaspring.controller;

import Alpha.alphaspring.DTO.RoutineRegisterRequestDto;
import Alpha.alphaspring.DTO.RoutineResponseDto;
import Alpha.alphaspring.DTO.SubRoutineRegisterRequestDto;
import Alpha.alphaspring.DTO.SubRoutineResponseDto;
import Alpha.alphaspring.service.RoutineServiceimpl;
import Alpha.alphaspring.service.SubRoutineServiceImpl;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RoutineController {
    private final RoutineServiceimpl routineService;

    private final SubRoutineServiceImpl subRoutineService;

    @Autowired
    public RoutineController(RoutineServiceimpl routineService, SubRoutineServiceImpl subRoutineService) {
        this.routineService = routineService;
        this.subRoutineService = subRoutineService;
    }

    @PostMapping("/routine")
    public ResponseEntity<String> postRoutine(
            @RequestHeader Map<String, Object> requestHeader,
            @RequestBody RoutineRegisterRequestDto requestBody
    ) throws ParseException {
        System.out.println("requestBody = " + requestBody);
        routineService.join(requestHeader, requestBody);

        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

    @GetMapping("/routines")
    public List<RoutineResponseDto> getRoutine(){
        try{
            return routineService.findRoutines();
        }catch (Exception e){
            System.out.println("/routines:e = " + e);
            return null;
        }
    }

    @PostMapping("/subRoutine")
    public ResponseEntity<String> postSubRoutine(
            @RequestHeader Map<String, Object> requestHeader,
            @RequestBody SubRoutineRegisterRequestDto requestBody
    ) throws ParseException {
        System.out.println("requestBody = " + requestBody);
        subRoutineService.join(requestHeader, requestBody);

        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

    @GetMapping("/subRoutines")
    public List<SubRoutineResponseDto> getSubRoutine(){
        try{
            return subRoutineService.findSubRoutines();
        }catch (Exception e){
            System.out.println("/routines:e = " + e);
            return null;
        }
    }
}
