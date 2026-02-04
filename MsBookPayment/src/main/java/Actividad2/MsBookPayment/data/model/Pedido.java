package Actividad2.MsBookPayment.data.model;
import Actividad2.MsBookPayment.data.utils.Consts;

import java.util.List;

import Actividad2.MsBookPayment.controller.model.PedidoDto;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "pedidos" )
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Consts.COMPRADOR)
    private String comprador;

@Column(name = Consts.LIBROS)
    private List<Long> libros;

    public void update (PedidoDto pedidoDto){
        this.comprador = pedidoDto.getComprador();
        this.libros = pedidoDto.getLibros();
    }
}
