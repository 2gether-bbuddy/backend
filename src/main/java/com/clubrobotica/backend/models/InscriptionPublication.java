package com.clubrobotica.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscripciones_publicacion")
public class InscriptionPublication {

    @EmbeddedId
    private InscriptionId id = new InscriptionId(); // Es buena práctica inicializarlo

    // === AQUÍ SUCEDE LA MAGIA DE LAS RELACIONES ===

    @ManyToOne
    @MapsId("controlNum") // Debe coincidir con el nombre de la variable dentro de InscriptionId
    @JoinColumn(name = "control_num")
    private User usuario; // Cambia "Usuario" por el nombre exacto de tu modelo

    @ManyToOne
    @MapsId("idPublication") // Debe coincidir con el nombre de la variable dentro de InscriptionId
    @JoinColumn(name = "id_publication")
    private Publication publicacion; // Cambia "Publicacion" por el nombre exacto de tu modelo

    // ==============================================

    private LocalDateTime dateInscription;
    private boolean physical_assistance;

    public InscriptionPublication() {
    }

    // ... (Tus getters y setters actuales para id, dateInscription y physical_assistance) ...

    // ¡No olvides agregar los getters y setters para los nuevos objetos!
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
}