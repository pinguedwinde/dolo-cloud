package webflux.repository;

import com.silga.dolocloud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByUsername(String username);
}

