package ma.enset.inventoryservice.query.controller;

import lombok.AllArgsConstructor;
import ma.enset.commonapi.queries.GetAllCategoriesQuery;
import ma.enset.commonapi.queries.GetCatgorieById;
import ma.enset.inventoryservice.query.entities.Categorie;
import ma.enset.inventoryservice.query.repositories.CategorieRepository;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categorie/queries")
@CrossOrigin("*")
public class CategorieQueryController {
    private QueryGateway queryGateway;
    private CategorieRepository categorieRepository;

    @GetMapping("/all")
    public List<Categorie> categories() {
        return queryGateway.query(new GetAllCategoriesQuery(), ResponseTypes.multipleInstancesOf(Categorie.class)).join();
    }

    @QueryHandler
    public List<Categorie> on(GetAllCategoriesQuery query) {
        return categorieRepository.findAll();
    }

    @GetMapping("/byId/{id}")
    public Categorie getCategorie(@PathVariable String id) {
        return queryGateway.query(new GetCatgorieById(id),ResponseTypes.instanceOf(Categorie.class)).join();
    }

    @QueryHandler
    public Categorie on(GetCatgorieById query) {
        return categorieRepository.findById(query.getId()).orElse(null);
    }
}
