package com.clubrobotica.backend.controllers;

import com.clubrobotica.backend.models.Career;
import com.clubrobotica.backend.services.CareerService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/careers")
@CrossOrigin("*")
public class CareerController {
    private final CareerService careerService;
    public CareerController(
        CareerService careerService){
        this.careerService = careerService;
    }
    
    @GetMapping
    public List<Career> getAll(){
        return careerService.getAll();
    }
    
    @PostMapping
    public Career save(
        @RequestBody Career career){
        return careerService.save(career);
    }
}
