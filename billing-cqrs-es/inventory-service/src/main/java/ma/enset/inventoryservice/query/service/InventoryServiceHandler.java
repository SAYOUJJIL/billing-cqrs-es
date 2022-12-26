package ma.enset.inventoryservice.query.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.commonapi.events.CategorieCreatedEvent;
import ma.enset.commonapi.events.CategorieUpdatedEvent;
import ma.enset.commonapi.events.ProductCreatedEvent;
import ma.enset.commonapi.events.ProductUpdatedEvent;

import ma.enset.inventoryservice.query.entities.Categorie;
import ma.enset.inventoryservice.query.entities.Product;
import ma.enset.inventoryservice.query.repositories.CategorieRepository;
import ma.enset.inventoryservice.query.repositories.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryServiceHandler {
    private final CategorieRepository categorieRepository;
    private final ProductRepository productRepository;

    @EventHandler
    public void on(CategorieCreatedEvent event) {
        log.info("*********************************");
        log.info("CategorieCreatedEvent received");
        Categorie categorie = new Categorie() ;
        categorie.setId(event.getId());
        categorie.setDescription(event.getDescription());
        categorie.setNom(event.getNom());
        categorieRepository.save(categorie) ;
    }

    @EventHandler
    public void on(CategorieUpdatedEvent event) {
        log.info("*********************************");
        log.info("CategorieUpdatedEvent received");
        Categorie categorie= categorieRepository.findById(event.getId()).get();
        categorie.setDescription(event.getDescription());
        categorie.setNom(event.getNom());
        categorieRepository.save(categorie) ;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        log.info("*********************************");
        log.info("ProductCreatedEvent received");
        Product product = new Product() ;
        product.setId(event.getId());
        product.setNom(event.getNom());
        product.setPrix(event.getPrix());
        product.setQte(event.getQteStock());
        product.setEtat(event.getEtat());

       Categorie categorie = categorieRepository.findById(event.getCategorieId()).get();
        if (categorie != null) {
            product.setCategorie(categorie);
        }
        productRepository.save(product) ;
    }

    @EventHandler
    public void on(ProductUpdatedEvent event) {
        log.info("*********************************");
        log.info("ProductUpdateddEvent received");
        Product product = productRepository.findById(event.getId()).get();
        product.setNom(event.getNom());
        product.setPrix(event.getPrix());
        product.setQte(event.getQteStock());
        product.setEtat(event.getEtat());

        Categorie categorie = categorieRepository.findById(event.getCategorieId()).get();

        product.setCategorie(categorie);

        productRepository.save(product) ;
    }

}
