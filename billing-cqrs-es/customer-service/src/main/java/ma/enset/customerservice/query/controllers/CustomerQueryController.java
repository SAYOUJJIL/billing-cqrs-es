package ma.enset.customerservice.query.controllers;

import lombok.AllArgsConstructor;
import ma.enset.commonapi.queries.GetAllCustomersQuery;
import ma.enset.commonapi.queries.GetCustomerById;
import ma.enset.customerservice.query.entities.Customer;
import ma.enset.customerservice.query.repositories.CustomerRepository;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/queries")
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerQueryController {
    private QueryGateway queryGateway;
    private CustomerRepository customerRepository;

    @GetMapping("/all")
    public List<Customer> customerList(){
        return queryGateway.query(new GetAllCustomersQuery(),
                ResponseTypes.multipleInstancesOf(Customer.class)).join();
    }
    @QueryHandler
    public List<Customer> on(GetAllCustomersQuery query){
        return customerRepository.findAll();
    }
    @GetMapping("/byId/{id}")
    public Customer customerById(@PathVariable String id){
        return queryGateway.query(new GetCustomerById(id),
                ResponseTypes.instanceOf(Customer.class)).join();
    }
    @QueryHandler
    public Customer on(GetCustomerById query){
        return customerRepository.findById(query
                .getId()).get();
    }
}
