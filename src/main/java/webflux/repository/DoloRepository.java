package webflux.repository;

import com.silga.dolocloud.model.Dolo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface DoloRepository extends ReactiveCrudRepository<Dolo, Long> {
}
