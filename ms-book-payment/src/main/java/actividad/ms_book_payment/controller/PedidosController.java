package actividad.ms_book_payment.controller;

import org.springframework.web.bind.annotation.RestController;

import actividad.ms_book_payment.controller.model.PedidoRequest;
import actividad.ms_book_payment.data.model.Pedido;
import actividad.ms_book_payment.service.PedidosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@Slf4j
// @Tag(name = "Pedidos Controller", description = "Microservicio encargado de
// realizar el pedido")
public class PedidosController {

    private final PedidosService service;

    /**
     * RQUEST ES
     * {
     * "libros": [1, 2, 3, 4, 5],
     * "comprador": "Juan Pérez"
     * }
     */
    @PostMapping("/pedidos")
    public ResponseEntity<Pedido> crearPedido(@RequestBody @Valid PedidoRequest request) {

        log.info("Recibido pedido para: {} con IDs: {}",
                request.getComprador(),
                request.getLibros());

        // Aquí puedes llamar a tu servicio con los datos recibidos
        Pedido pedido = service.crearPedido(request);

        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> getPedidos() {
        List<Pedido> pedidos = service.getPedidos();
        if (pedidos != null) {
            return ResponseEntity.ok(pedidos);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

}
