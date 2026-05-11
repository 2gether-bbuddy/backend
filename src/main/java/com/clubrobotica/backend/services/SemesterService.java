package com.clubrobotica.backend.services;

import com.clubrobotica.backend.models.Semester;
import com.clubrobotica.backend.repositories.SemesterRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SemesterService {
    private final SemesterRepository semesterRepository;
    
    public SemesterService(SemesterRepository semesterRepository){
        this.semesterRepository = semesterRepository;
    }
    
    public List<Semester> getAll(){
        return semesterRepository.findAll();
    }
    
    public Semester save(Semester semester){
        return semesterRepository.save(semester);
    }
}
