package com.prueba.farma.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.prueba.farma.model.Ticket;
import com.prueba.farma.model.Ticket.Estatus;
import com.prueba.farma.repository.TicketRepository;

public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    private Ticket ticket;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Configuración inicial del ticket
        ticket = new Ticket();
        ticket.setId(UUID.randomUUID());
        ticket.setEstatus(Estatus.ABIERTO); // Enum definido en el modelo
        ticket.setFechaCreacion(LocalDateTime.now());
        ticket.setFechaActualizacion(LocalDateTime.now());
    }

    @Test
    void testCrearTicket() {
        // Configurar el comportamiento simulado del repositorio
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        // Ejecutar la operación
        Ticket nuevoTicket = ticketService.crearTicket(ticket);

        // Verificaciones
        assertNotNull(nuevoTicket);
        assertEquals(Estatus.ABIERTO, nuevoTicket.getEstatus());
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void testObtenerTicketPorId() {
        // Configurar el repositorio para devolver un ticket
        when(ticketRepository.findById(ticket.getId())).thenReturn(Optional.of(ticket));

        // Llamar al servicio para obtener el ticket por ID
        Ticket resultado = ticketService.obtenerTicketId(ticket.getId());

        // Verificaciones
        assertNotNull(resultado);
        assertEquals(ticket.getId(), resultado.getId());
        assertEquals(Estatus.ABIERTO, resultado.getEstatus());
        verify(ticketRepository, times(1)).findById(ticket.getId());
    }

    @Test
    void testEliminarTicket() {
        // Configurar comportamiento simulado para eliminación
        doNothing().when(ticketRepository).deleteById(ticket.getId());

        // Ejecutar la operación
        ticketService.eliminarTicket(ticket.getId());

        // Verificar que el repositorio se llamó correctamente
        verify(ticketRepository, times(1)).deleteById(ticket.getId());
    }

    @Test
    void testActualizarTicket() {
        // Simular que el ticket existe en la base de datos
        when(ticketRepository.findById(ticket.getId())).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        // Actualizar el estatus del ticket
        ticket.setEstatus(Estatus.CERRADO);

        // Ejecutar la operación de actualización
        Ticket actualizado = ticketService.editarTicket(ticket.getId(), ticket);

        // Verificaciones
        assertNotNull(actualizado);
        assertEquals(Estatus.CERRADO, actualizado.getEstatus());
        verify(ticketRepository, times(1)).save(ticket);
    }
}
