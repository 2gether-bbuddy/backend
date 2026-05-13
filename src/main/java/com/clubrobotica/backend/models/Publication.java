package com.clubrobotica.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "publicaciones") // Ajustado a tu foto
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPublication;

    // ¡Ojo! Escrito con doble 't' para que coincida con tu base de datos
    private String tittle;

    private String description;

    private String type;

    // Campos que vi en tu foto
    private Boolean doInscription;
    private Integer capacity;

    @Column(name = "num_control_autor")
    private String numControlAutor;

    // La fecha del evento (¡RECUERDA AGREGAR ESTA COLUMNA EN PGADMIN!)
    @Column(name = "event_date")
    private String eventDate;

    private LocalDateTime dateCreation;

    public Publication() {
    }

    // Getters y Setters
    public Integer getIdPublication() { return idPublication; }
    public void setIdPublication(Integer idPublication) { this.idPublication = idPublication; }

    public String getTittle() { return tittle; }
    public void setTittle(String tittle) { this.tittle = tittle; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Boolean getDoInscription() { return doInscription; }
    public void setDoInscription(Boolean doInscription) { this.doInscription = doInscription; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public String getNumControlAutor() { return numControlAutor; }
    public void setNumControlAutor(String numControlAutor) { this.numControlAutor = numControlAutor; }

    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
}