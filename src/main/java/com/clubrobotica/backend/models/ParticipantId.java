package com.clubrobotica.backend.models;

import jakarta.persistence.Embeddable; // <-- Faltaba este import
import java.io.Serializable;
import java.util.Objects;

@Embeddable // <-- Esta etiqueta es OBLIGATORIA
public class ParticipantId implements Serializable {

    private Integer idConversation;
    private String controlNumber; // <-- Ya corregido con la 'r'

    public ParticipantId() {
    }

    public Integer getIdConversation() {
        return idConversation;
    }

    public void setIdConversation(Integer idConversation) {
        this.idConversation = idConversation;
    }

    public String getControlNumber() {
        return controlNumber; // <-- Actualizado
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber; // <-- Actualizado
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipantId)) return false;
        ParticipantId that = (ParticipantId) o;
        // Se actualizó también aquí abajo
        return Objects.equals(idConversation, that.idConversation) &&
                Objects.equals(controlNumber, that.controlNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConversation, controlNumber); // <-- Actualizado
    }
}