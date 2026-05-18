package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.clubrobotica.backend.models.User;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {

    List<Publication> findByType(String type);

    // CONSULTA NATIVA: Busca los usuarios inscritos a través de la tabla intermedia
    @Query(value = "SELECT u.* FROM usuarios u " +
            "JOIN inscripciones_publicacion i ON u.control_number = i.control_num " +
            "WHERE i.id_publication = :idPublication", nativeQuery = true)
    List<User> findParticipantsByPublicationId(@Param("idPublication") Integer idPublication);
}