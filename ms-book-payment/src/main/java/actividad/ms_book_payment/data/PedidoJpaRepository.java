package actividad.ms_book_payment.data;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import actividad.ms_book_payment.data.model.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoJpaRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {
}

