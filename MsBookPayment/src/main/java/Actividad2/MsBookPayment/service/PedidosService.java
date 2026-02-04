package Actividad2.MsBookPayment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Actividad2.MsBookPayment.data.PedidoRepository;
import Actividad2.MsBookPayment.data.model.Pedido;
import lombok.AllArgsConstructor;


public interface  PedidosService {
        Pedido creatPedido(String comprador, List<Long> ids);

}
        
