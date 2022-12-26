package ma.enset.customerservice.query.repositories;

import ma.enset.customerservice.query.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}