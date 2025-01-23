package com.prueba.farma.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.farma.model.Ticket;

public interface TicketRepository extends JpaRepository <Ticket, UUID> {

    List<Ticket> findByEstatus(Ticket.Estatus estatus);

    List<Ticket> findByUsuarioId(UUID usuarioId);

    List<Ticket> findByEstatusAndUsuarioId(Ticket.Estatus estatus, UUID usuarioId);
}
