package com.prueba.farma.controller;

import org.springframework.web.bind.annotation.RestController;

import com.prueba.farma.model.Ticket;
import com.prueba.farma.service.TicketService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@Valid @RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.crearTicket(ticket));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> editarTicket(@PathVariable UUID id, @Valid @RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.editarTicket(id, ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTicket(@PathVariable UUID id) {
        ticketService.eliminarTicket(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> obtenerTicketPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ticketService.obtenerTicketId(id));
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> obtenerTicketsPaginados(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ticketService.obtenerTicketsPaginados(page, size));
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Ticket>> filtrarTickets(@RequestParam(required = false) String estatus,
                                                       @RequestParam(required = false) UUID usuarioId) throws BadRequestException {
        return ResponseEntity.ok(ticketService.filtrarTickets(estatus, usuarioId));
    }

}
