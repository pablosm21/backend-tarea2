package Actividad2.MsBookPayment.controller.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {
    private List<Long> libros;
    private String comprador;
}
