package com.clubrobotica.backend.services;

import com.clubrobotica.backend.models.AcademicHistory;
import com.clubrobotica.backend.repositories.AcademicHistoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AcademicHistoryService {
    private final AcademicHistoryRepository historyRepository;
    
    public AcademicHistoryService(
            AcademicHistoryRepository historyRepository){
        this.historyRepository = historyRepository;
    }
    
    public AcademicHistory save(
        AcademicHistory history){
        return historyRepository.save(history);
    }
    
    public List<AcademicHistory> getByUser(
        String control_number){
        return (List<AcademicHistory>) historyRepository.findByUser(control_number);
    }
}
