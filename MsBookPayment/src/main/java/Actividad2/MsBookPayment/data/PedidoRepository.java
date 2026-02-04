package Actividad2.MsBookPayment.data;

import java.util.List;

import org.springframework.stereotype.Repository;

import Actividad2.MsBookPayment.data.model.Pedido;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PedidoRepository {
    
    private final PedidoJpaRepository repository;

    public Pedido crearPedido(Pedido pedido){return repository.save(pedido);}
    public List<Pedido> getAllPedidos(){return repository.findAll();}
}
