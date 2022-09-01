package Alpha.alphaspring.controller;

import Alpha.alphaspring.DTO.*;
import Alpha.alphaspring.service.RoutineService;
import Alpha.alphaspring.service.SubRoutineService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
public class RoutineController {
    private final RoutineService routineService;

    private final SubRoutineService subRoutineService;

    @Autowired
    public RoutineController(RoutineService routineService, SubRoutineService subRoutineService) {
        this.routineService = routineService;
        this.subRoutineService = subRoutineService;
    }

    @PostMapping("/routine")
    public StringResponseDto postRoutine(
            @RequestBody RoutineRegisterRequestDto requestBody
    ) throws ParseException {
        System.out.println("requestBody = " + requestBody);
        routineService.join(requestBody);
        return new StringResponseDto("Routine Registered!!");
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
    public StringResponseDto postSubRoutine(
            @RequestBody SubRoutineRegisterRequestDto requestBody
    ) throws ParseException {
        System.out.println("requestBody = " + requestBody);
        subRoutineService.join(requestBody);
        return new StringResponseDto("SubRoutine Registered!!");
    }

    @GetMapping("/subRoutines")
    public List<SubRoutineResponseDto> getSubRoutine(){
        try{
            return subRoutineService.findSubRoutines();
        }catch (Exception e){
            System.out.println("/subRoutines:e = " + e);
            return null;
        }
    }
}
