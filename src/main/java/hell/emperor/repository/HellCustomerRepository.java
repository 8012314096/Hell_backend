package hell.emperor.repository;

import hell.emperor.entity.HellCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.Optional;

public interface HellCustomerRepository extends JpaRepository<HellCustomer, Long> {
    Optional<HellCustomer> findByPhone(String phone);

    String query="SELECT c FROM HellCustomer c WHERE c.name = :name";
    @Query(query)
    List<HellCustomer> findByName(@Param("name") String name);
}
