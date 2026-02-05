package actividad.ms_book_payment.controller.model;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "`libros` cannot be null")
	@NotEmpty(message = "`libros` cannot be empty")
    private List<Long> libros;

    @NotNull(message = "`comprador` cannot be null")
	@NotEmpty(message = "`comprador` cannot be empty")
    private String comprador;
    
}
