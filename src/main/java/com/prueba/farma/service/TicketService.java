package com.prueba.farma.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prueba.farma.excepciones.InternalServerErrorException;
import com.prueba.farma.model.Ticket;
import com.prueba.farma.model.Ticket.Estatus;
import com.prueba.farma.repository.TicketRepository;

import jakarta.persistence.EnumType;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private static final Logger log = LoggerFactory.getLogger(TicketService.class);


    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket crearTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket editarTicket(UUID id, Ticket ticket) {
        Ticket ticketActualizar = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El Ticket con ID" + id + " que desea Actualizar no Existe"));
        ticketActualizar.setEstatus(ticket.getEstatus());
        ticketActualizar.setFechaActualizacion(LocalDateTime.now());

        return ticketRepository.save(ticketActualizar);
    }

    public void eliminarTicket(UUID id) {
        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException("El ticket con Id " + id + "No existe");
        }
        ticketRepository.deleteById(id);
    }

    public Ticket obtenerTicketId(UUID id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El Ticket con ID " + id + " No Existe"));
    }

    public List<Ticket> obtenerTicketsPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ticketRepository.findAll(pageable).getContent();
    }

    public List<Ticket> filtrarTickets(String estatus, UUID usuarioId) throws BadRequestException {
        try {
                log.info("Filtrando tickets con estatus={} y usuarioId={}", estatus, usuarioId);

                if (estatus != null && usuarioId != null) {
                    return ticketRepository.findByEstatusAndUsuarioId(Estatus.valueOf(estatus.toUpperCase()), usuarioId);
                } else if (estatus != null) {
                    return ticketRepository.findByEstatus(Estatus.valueOf(estatus.toUpperCase()));
                } else if (usuarioId != null) {
                    return ticketRepository.findByUsuarioId(usuarioId);
                } else {
                    return ticketRepository.findAll();
                }
            } catch (IllegalArgumentException e) {
                log.error("Estatus inválido: {}", estatus, e);
                throw new BadRequestException("El estatus proporcionado no es válido: " + estatus, e);
            } catch (Exception e) {
                log.error("Error inesperado al filtrar tickets", e);
                throw new InternalServerErrorException("Ocurrió un error al filtrar los tickets.", e);
            }
    }

}
