package Actividad2.MsBookPayment.controller;

import org.springframework.web.bind.annotation.RestController;

import Actividad2.MsBookPayment.controller.model.PedidoRequest;
import Actividad2.MsBookPayment.data.model.Pedido;
import Actividad2.MsBookPayment.service.PedidoServiceImpl;
import Actividad2.MsBookPayment.service.PedidoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@Slf4j
// @Tag(name = "Pedidos Controller", description = "Microservicio encargado de
// realizar el pedido")
public class PedidosController {

    private final PedidoServiceImpl service;

    @PostMapping("/crearPedido")
    public String crearPedido(@RequestBody PedidoRequest request) {
        /**
         * RQUEST ES
         * {
         * "libros": [1, 2, 3, 4, 5],
         * "comprador": "Juan Pérez"
         * }
         */
        log.info("Recibido pedido para: {} con IDs: {}",
                request.getComprador(),
                request.getLibros());

        // Aquí puedes llamar a tu servicio con los datos recibidos
        Pedido pedido = service.creatPedido(request.getComprador(), request.getLibros());

        if (pedido != null) {
            return "Pedido procesado para " + request.getComprador() +
                    " con " + request.getLibros().size() + " items";
        } else {
            return "Pedido erroneo";
        }

    }

    @GetMapping("/pedidos")
    public String getAllPedidos() {
        return service.getAllPedidos().toString();
    }

}
