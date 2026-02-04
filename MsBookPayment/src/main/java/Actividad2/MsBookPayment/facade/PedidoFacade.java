package Actividad2.MsBookPayment.facade;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import Actividad2.MsBookPayment.data.model.Pedido;
import Actividad2.MsBookPayment.facade.model.LibrosRequest;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoFacade {

    @Value("http://ms-books-catalogue")
    private String getPedidosUrl;

    private final WebClient.Builder webClient;

    public boolean validarPedido(List<Long> ids) {

        try {
            LibrosRequest body = new LibrosRequest(ids);
            // Construir la URL con los parámetros
            log.info("Llamando a validarPedido con libros: {}", ids);

            webClient.build()
                    .post()
                    .uri(getPedidosUrl + "/books/availability")
                    .bodyValue(body)
                    .retrieve()
                    .toBodilessEntity()
                    .block();

        return true; // si llega aquí → 2xx
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, IDs: {}", e.getStatusCode(), ids);
            return false;
        } catch (HttpServerErrorException e) {
            log.error("Server Error: {}, IDs: {}", e.getStatusCode(), ids);
            return false;
        } catch (Exception e) {
            log.error("Error: {}, IDs: {}", e.getMessage(), ids);
            return false;
        }

    }
}
