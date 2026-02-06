package actividad.ms_book_payment.mapper;

import org.springframework.stereotype.Component;

import actividad.ms_book_payment.controller.model.PedidoDto;
import actividad.ms_book_payment.controller.model.PedidoRequest;
import actividad.ms_book_payment.data.model.Pedido;

@Component
public class PedidoMapper {
    // Request → Entity
    public Pedido toEntity(PedidoRequest request) {
        if (request == null) {
            return null;
        }

        Pedido pedido = new Pedido();
        pedido.setComprador(request.getComprador());
        pedido.setLibros(request.getLibros());

        return pedido;
    }

    // Entity → Response DTO
    public PedidoDto toDto(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        PedidoDto dto = new PedidoDto();
        dto.setComprador(pedido.getComprador());
        dto.setLibros(pedido.getLibros());

        return dto;
    }
}
