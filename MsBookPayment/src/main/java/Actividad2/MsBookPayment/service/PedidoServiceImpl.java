package Actividad2.MsBookPayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Actividad2.MsBookPayment.data.PedidoJpaRepository;
import Actividad2.MsBookPayment.data.PedidoRepository;
import Actividad2.MsBookPayment.data.model.Pedido;
import Actividad2.MsBookPayment.facade.PedidoFacade;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidosService {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private PedidoFacade pedidosFacade;

    @Override
    public Pedido creatPedido(String comprador,List<Long> libros) {
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

    public List<Pedido> getAllPedidos(){
        return repository.getAllPedidos();
    }
}