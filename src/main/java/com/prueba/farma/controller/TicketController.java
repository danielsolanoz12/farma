package com.prueba.farma.controller;

import org.springframework.web.bind.annotation.RestController;

import com.prueba.farma.model.Ticket;
import com.prueba.farma.service.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import org.springframework.web.bind.annotation.RequestParam;

/**
 * API TICKETS
 * Controlador REST para gestionar los tickets de la aplicación.
 * Proporciona endpoints para filtrar, crear y gestionar tickets.
 * 
 * @author [Daniel Solano]
 * @version 1.0
 */
@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Crea un nuevo ticket.
     *
     * @param ticket Objeto del ticket a crear.
     * @return El ticket creado con su ID asignado.
     */
    @Operation(
        summary = "Crear ticket",
        description = "Crear un nuevo ticket en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket creado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class))),
        @ApiResponse(responseCode = "400", description = "Datos del ticket no válidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@Valid @RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.crearTicket(ticket));
    }

    /**
     * Crea un nuevo ticket.
     *
     * @param ticketId Identificador del ticket a editar.
     * @param ticket Objeto del ticket a editar.
     * @return El ticket creado con su ID asignado.
     */
    @Operation(
        summary = "Editar ticket",
        description = "Editar o actualizar ticket en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket ha sido editado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class))),
        @ApiResponse(responseCode = "400", description = "Datos del ticket no válidos", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> editarTicket(@PathVariable UUID id, @Valid @RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.editarTicket(id, ticket));
    }

     /**
     * Eliminar un ticket.
     *
     * @param ticketId Identificador único del ticket a eliminar.
     */
    @Operation(
        summary = "Eliminar ticket",
        description = "Elimina un ticket por su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "El ticket no fue encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTicket(@PathVariable UUID id) {
        ticketService.eliminarTicket(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtiene un ticket por su ID.
     *
     * @param ticketId Identificador único del ticket.
     * @return El ticket correspondiente o un error si no existe.
     */
    @Operation(
        summary = "Obtener ticket por ID",
        description = "Obtiene los detalles de un ticket en específico utilizando su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class))),
        @ApiResponse(responseCode = "404", description = "El ticket no fue encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> obtenerTicketPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ticketService.obtenerTicketId(id));
    }

    /**
     * Obtener una lista paginada de Tickets.
     *
     * @param page   Numero de paginas.
     * @param size Tamaño de la lista de tickets a visualizar en la pagina.
     * @return Lista de tickets organizados.
     */
    @Operation(
        summary = "lista paginada de Tickets",
        description = "Obtiene una lista de tickets según organizada en paginas y por numero  te tickets a mostrar en la pagina."
    )
    @GetMapping
    public ResponseEntity<List<Ticket>> obtenerTicketsPaginados(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ticketService.obtenerTicketsPaginados(page, size));
    }

    /**
     * Filtra los tickets por estado y/o usuario.
     *
     * @param estatus   Estado del ticket (opcional). Ejemplo: "ABIERTO", "CERRADO".
     * @param usuarioId Identificador del usuario asociadoa l ticket (opcional).
     * @return Lista de tickets que cumplen con los criterios de filtrado.
     */
    @Operation(
        summary = "Filtrar tickets",
        description = "Obtiene una lista de tickets según su estado o usuario. Si no se proporcionan filtros, devuelve todos los tickets."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de tickets filtrados",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class))),
        @ApiResponse(responseCode = "400", description = "Criterios de filtrado no válidos", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/filtrar")
    public ResponseEntity<List<Ticket>> filtrarTickets(@RequestParam(required = false) String estatus,
                                                       @RequestParam(required = false) UUID usuarioId) throws BadRequestException {
        return ResponseEntity.ok(ticketService.filtrarTickets(estatus, usuarioId));
    }

}
