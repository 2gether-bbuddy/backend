package com.clubrobotica.backend.services;

import com.clubrobotica.backend.models.Period;
import com.clubrobotica.backend.repositories.PeriodRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PeriodService {
    private final PeriodRepository periodRepository;
    
    public PeriodService(PeriodRepository periodRepository){
        this.periodRepository = periodRepository;
    }
    
    public List<Period> getAll(){
        return periodRepository.findAll();
    }
    
    public Period save(Period period){
        return periodRepository.save(period);
    }
}
