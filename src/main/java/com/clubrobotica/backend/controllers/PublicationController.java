package com.clubrobotica.backend.controllers;

import com.clubrobotica.backend.models.Publication;
import com.clubrobotica.backend.services.PublicationService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publications")
@CrossOrigin("*")
public class PublicationController {
    private final PublicationService publicationService;
    public PublicationController(
        PublicationService publicationService){
        this.publicationService = publicationService;
    }
    
    @GetMapping
    public List<Publication> getAll(){
        return publicationService.getPublication();
    }
    
    @PostMapping
    public Publication save(
        @RequestBody Publication publication){
        return publicationService.savePublication(publication);
    }
}
