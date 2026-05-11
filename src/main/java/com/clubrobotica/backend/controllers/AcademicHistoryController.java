package com.clubrobotica.backend.controllers;

import com.clubrobotica.backend.models.AcademicHistory;
import com.clubrobotica.backend.services.AcademicHistoryService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/academicHistory")
@CrossOrigin("*")
public class AcademicHistoryController {
    private final AcademicHistoryService academicHistoryService;
    public AcademicHistoryController(
        AcademicHistoryService academicHistoryService){
        this.academicHistoryService = academicHistoryService;
    }
    
    @GetMapping("/{controlNumber}")
    public List<AcademicHistory> getAll(
            @PathVariable String control_number){
        return academicHistoryService.getByUser(control_number);
    }
    
    @PostMapping
    public AcademicHistory save(
        @RequestBody AcademicHistory academicHistory){
        return academicHistoryService.save(academicHistory);
    }
}
