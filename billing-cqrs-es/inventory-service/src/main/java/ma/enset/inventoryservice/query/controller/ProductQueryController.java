package ma.enset.inventoryservice.query.controller;

import lombok.AllArgsConstructor;
import ma.enset.commonapi.queries.GetAllProductsQuery;
import ma.enset.commonapi.queries.GetProductById;
import ma.enset.inventoryservice.query.entities.Product;
import ma.enset.inventoryservice.query.repositories.ProductRepository;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product/queries")
@CrossOrigin("*")
public class ProductQueryController {
    private QueryGateway queryGateway;
    private ProductRepository productRepository;

    @GetMapping("/all")
    public List<Product> products() {
        return queryGateway.query(new GetAllProductsQuery(), ResponseTypes.multipleInstancesOf(Product.class)).join();
    }

    @QueryHandler
    public List<Product> on(GetAllProductsQuery query) {
        return productRepository.findAll();
    }

    @GetMapping("/byId/{id}")
    public Product getVehicule(@PathVariable String id) {
        return queryGateway.query(new GetProductById(id),ResponseTypes.instanceOf(Product.class)).join();
    }

    @QueryHandler
    public Product on(GetProductById query) {
        return productRepository.findById(query.getId()).orElse(null);
    }
}
