package com.clubrobotica.backend.services;

import com.clubrobotica.backend.models.AcademicHistory;
import com.clubrobotica.backend.models.Period;
import com.clubrobotica.backend.models.Semester;
import com.clubrobotica.backend.repositories.AcademicHistoryRepository;
import com.clubrobotica.backend.repositories.PeriodRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AcademicAutomationService {

    private final PeriodRepository periodRepository;
    private final AcademicHistoryRepository historyRepository;

    public AcademicAutomationService(PeriodRepository periodRepository, AcademicHistoryRepository historyRepository) {
        this.periodRepository = periodRepository;
        this.historyRepository = historyRepository;
    }

    /**
     * Expresión CRON: "0 0 3 15 2,8 ?"
     * Segundos(0) Minutos(0) Horas(3) Día(15) Meses(Febrero y Agosto) DíaDeLaSemana(Cualquiera)
     */
    @Scheduled(cron = "0 0 3 15 2,8 ?")
    @Transactional
    public void automatizarCicloEscolar() {
        System.out.println(" [SISTEMA] Iniciando actualización del Ciclo Escolar...");

        LocalDate hoy = LocalDate.now();
        int anioActual = hoy.getYear();
        int mesActual = hoy.getMonthValue();

        // 1. Determinar el ciclo oficial
        String nombreCiclo = (mesActual <= 7) ? "Enero-Junio " + anioActual : "Agosto-Diciembre " + anioActual;

        // 2. Buscar si el periodo ya existe en la BD. Si no, lo crea automáticamente.
        Period periodoActual = periodRepository.findByNamePeriod(nombreCiclo)
                .orElseGet(() -> {
                    Period nuevoPeriodo = new Period();
                    nuevoPeriodo.setName_period(nombreCiclo);
                    return periodRepository.save(nuevoPeriodo);
                });

        // 3. Traer los historiales de todos los alumnos activos
        List<AcademicHistory> historialesActivos = historyRepository.findByCurrentTrue();
        List<AcademicHistory> nuevosHistoriales = new ArrayList<>();

        for (AcademicHistory historialViejo : historialesActivos) {
            // "Apagamos" el semestre anterior para que quede como registro histórico
            historialViejo.setCurrent(false);

            // Creamos el nuevo registro para el semestre que empieza
            AcademicHistory historialNuevo = new AcademicHistory();
            historialNuevo.setUser(historialViejo.getUser());
            historialNuevo.setPeriod(periodoActual);
            historialNuevo.setCurrent(true); // Este es ahora el semestre actual del alumno

            // Lógica para subir de semestre
            Semester semestreViejo = historialViejo.getSemester();
            if (semestreViejo != null) {
                Semester semestreNuevo = new Semester();

                // Si el ID del semestre es menor a 13 ("Más"), le sumamos 1. Si no, se queda en 13.
                if (semestreViejo.getIdSemester() < 13) {
                    semestreNuevo.setIdSemester(semestreViejo.getIdSemester() + 1);
                } else {
                    semestreNuevo.setIdSemester(13);
                }

                historialNuevo.setSemester(semestreNuevo);
            }

            nuevosHistoriales.add(historialNuevo);
        }

        // 4. Guardamos todos los cambios (los viejos apagados y los nuevos encendidos)
        historyRepository.saveAll(historialesActivos);
        historyRepository.saveAll(nuevosHistoriales);

        System.out.println("[SISTEMA] Actualización completada. Alumnos movidos al periodo: " + nombreCiclo);
    }
}