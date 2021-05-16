package webflux.router;

import com.silga.dolocloud.model.Dolo;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import webflux.repository.DoloRepository;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


public class DoloRouter {
    private final DoloRepository doloRepository;

    public DoloRouter(DoloRepository doloRepository) {
        this.doloRepository = doloRepository;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return route(GET("/design/dolo"), this::getRecentDolos)
                .andRoute(POST("/design/"), this::saveDolo);
    }

    public Mono<ServerResponse> getRecentDolos(ServerRequest request) {
        return ServerResponse.ok()
                .body(doloRepository.findAll().take(12), Dolo.class);
    }
    public Mono<ServerResponse> saveDolo(ServerRequest request) {
        Mono<Dolo> dolo = request.bodyToMono(Dolo.class);
        Mono<Dolo> savedDolo = dolo.flatMap(doloRepository::save);
        return ServerResponse
                .status(HttpStatus.CREATED)
                .body(savedDolo, Dolo.class);
    }
}
