package webflux.repository;

import com.silga.dolocloud.model.DoloOrder;
import com.silga.dolocloud.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface OrderRepository extends ReactiveCrudRepository<DoloOrder, Long> {

    Flux<DoloOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
