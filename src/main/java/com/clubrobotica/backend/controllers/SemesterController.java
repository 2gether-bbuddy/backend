package com.clubrobotica.backend.controllers;

import com.clubrobotica.backend.models.Semester;
import com.clubrobotica.backend.services.SemesterService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/semester")
@CrossOrigin("*")
public class SemesterController {
    private final SemesterService semesterService;
    public SemesterController(
            SemesterService semesterService){
        this.semesterService = semesterService;
    }

    @GetMapping
    public List<Semester> getAll(){
        return semesterService.getAll();
    }

    @PostMapping
    public Semester save(
            @RequestBody Semester semester){
        return semesterService.save(semester);
    }
}
