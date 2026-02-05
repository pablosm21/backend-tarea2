package actividad.ms_book_payment.service;

import org.springframework.stereotype.Service;

import actividad.ms_book_payment.controller.model.PedidoRequest;
import actividad.ms_book_payment.data.PedidoRepository;
import actividad.ms_book_payment.data.model.Pedido;
import actividad.ms_book_payment.facade.PedidosFacade;

import java.util.List;

@Service
public class PedidosServiceImpl implements PedidosService {

    private final PedidosFacade pedidosFacade;
    private final PedidoRepository repository;

    public PedidosServiceImpl(PedidosFacade pedidosFacade, PedidoRepository repository) {
        this.pedidosFacade = pedidosFacade;
        this.repository = repository;
    }

    @Override
    public Pedido crearPedido(PedidoRequest request) {

        List<Long> libros = request.getLibros();
        String comprador = request.getComprador();

        boolean valido = pedidosFacade.validarPedido(libros);

        if (valido) {
            Pedido pedido = new Pedido();
            pedido.setComprador(comprador);
            pedido.setLibros(libros);

            repository.crearPedido(pedido);

            return pedido;
        } else {
            return null;
        }
    }

    public List<Pedido> getPedidos() {
        return repository.getAllPedidos();
    }
}