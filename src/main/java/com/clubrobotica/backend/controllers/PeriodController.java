package com.clubrobotica.backend.controllers;

import com.clubrobotica.backend.models.Period;
import com.clubrobotica.backend.services.PeriodService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/period")
@CrossOrigin("*")
public class PeriodController {
    private final PeriodService periodService;
    public PeriodController(
        PeriodService periodService){
        this.periodService = periodService;
    }
    
    @GetMapping
    public List<Period> getAll(){
        return periodService.getAll();
    }
    
    @PostMapping
    public Period save(
        @RequestBody Period period){
        return periodService.save(period);
    }
}
