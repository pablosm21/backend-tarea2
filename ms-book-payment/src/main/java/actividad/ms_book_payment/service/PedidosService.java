package actividad.ms_book_payment.service;

import java.util.List;

import actividad.ms_book_payment.controller.model.PedidoRequest;

import actividad.ms_book_payment.data.model.Pedido;

public interface PedidosService {

        Pedido crearPedido(PedidoRequest request);

        List<Pedido> getPedidos();

}
