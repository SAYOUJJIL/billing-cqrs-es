package ma.enset.customerservice.query.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.commonapi.events.CustomerCreatedEvent;
import ma.enset.commonapi.events.CustomerUpdatedEvent;
import ma.enset.customerservice.query.entities.Customer;
import ma.enset.customerservice.query.repositories.CustomerRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceHandler {
    private CustomerRepository customerRepository;

    @EventHandler
    public void on(CustomerCreatedEvent event){
        log.info("**************************");
        log.info("CustomerCreatedEvent received");
        customerRepository.save(new Customer(
                event.getId(),
                event.getNom(),
                event.getAdresse(),
                event.getEmail(),
                event.getTel()));
    }

    @EventHandler
    public void on(CustomerUpdatedEvent event){
        log.info("**************************");
        log.info("CustomerUpdatedEvent received");
        Customer customer = customerRepository.findById(event.getId()).get();
        customer.setNom(event.getNom());
        customer.setAdresse(event.getAdresse());
        customer.setEmail(event.getEmail());
        customer.setTel(event.getTel());
        customerRepository.save(customer);
    }
}
