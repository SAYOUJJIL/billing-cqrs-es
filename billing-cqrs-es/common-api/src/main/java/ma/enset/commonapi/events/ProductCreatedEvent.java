package ma.enset.commonapi.events;

import lombok.Getter;
import ma.enset.commonapi.enums.ProductEtat;

public class ProductCreatedEvent extends BaseEvent<String> {
    @Getter private String nom ;
    @Getter private double prix ;
    @Getter private  int qteStock  ;
    @Getter private ProductEtat etat ;
    @Getter private String categorieId;

    public ProductCreatedEvent(String id, String nom, double prix, int qteStock, ProductEtat etat, String categorieId) {
        super(id);
        this.nom = nom;
        this.prix = prix;
        this.qteStock = qteStock;
        this.etat = etat;
        this.categorieId = categorieId;
    }
}
