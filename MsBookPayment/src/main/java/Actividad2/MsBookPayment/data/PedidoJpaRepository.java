package Actividad2.MsBookPayment.data;
import Actividad2.MsBookPayment.data.model.Pedido;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoJpaRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {
}

