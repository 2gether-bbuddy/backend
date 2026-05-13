package com.clubrobotica.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "participante_conversacion")
public class Participant_Conversation {

    @EmbeddedId
    private ParticipantId id = new ParticipantId(); // Inicializamos el ID

    // === MAGIA DE LAS RELACIONES ===

    @ManyToOne
    @MapsId("controlNumber") // Debe coincidir exactamente con la variable en ParticipantId
    @JoinColumn(name = "control_number") // ¡Corregimos el typo de SQL aquí!
    private User usuario;

    @ManyToOne
    @MapsId("idConversation") // Debe coincidir exactamente con la variable en ParticipantId
    @JoinColumn(name = "id_conversation")
    private Conversation conversacion; // Cambia "Conversation" si tu modelo se llama distinto

    // ===============================

    private LocalDateTime entry_date;

    public Participant_Conversation() {
    }

    public ParticipantId getId() {
        return id;
    }

    public void setId(ParticipantId id) {
        this.id = id;
    }

    public LocalDateTime getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(LocalDateTime entry_date) {
        this.entry_date = entry_date;
    }

    // --- Nuevos Getters y Setters para los objetos ---

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Conversation getConversacion() {
        return conversacion;
    }

    public void setConversacion(Conversation conversacion) {
        this.conversacion = conversacion;
    }
}