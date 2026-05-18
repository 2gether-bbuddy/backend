package com.clubrobotica.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscripciones_publicacion")
public class InscriptionPublication {

    @EmbeddedId
    private InscriptionId id = new InscriptionId();

    @ManyToOne
    @MapsId("controlNum")
    @JoinColumn(name = "control_num")
    private User usuario;

    @ManyToOne
    @MapsId("idPublication")
    @JoinColumn(name = "id_publication")
    private Publication publicacion;

    private LocalDateTime dateInscription;
    private boolean physical_assistance;

    public InscriptionPublication() {
    }

    public InscriptionId getId() {
        return id;
    }

    public void setId(InscriptionId id) {
        this.id = id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Publication getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publication publicacion) {
        this.publicacion = publicacion;
    }

    public LocalDateTime getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDateTime dateInscription) {
        this.dateInscription = dateInscription;
    }

    public boolean isPhysical_assistance() {
        return physical_assistance;
    }

    public void setPhysical_assistance(boolean physical_assistance) {
        this.physical_assistance = physical_assistance;
    }
}