package com.clubrobotica.backend.services;

import com.clubrobotica.backend.models.Career;
import com.clubrobotica.backend.repositories.CareerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CareerService {
    private final CareerRepository careerRepository;
    
    public CareerService(CareerRepository careerRepository){
        this.careerRepository = careerRepository;
    }
    
    public List<Career> getAll(){
        return careerRepository.findAll();
    }
    
    public Career save(Career career){
        return careerRepository.save(career);
    }
}
