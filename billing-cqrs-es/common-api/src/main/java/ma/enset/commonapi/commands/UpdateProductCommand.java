package ma.enset.commonapi.commands;

import lombok.Getter;
import ma.enset.commonapi.enums.ProductEtat;

public class UpdateProductCommand extends  BaseCommand<String>{

   @Getter private String nom ;
   @Getter private double prix ;
   @Getter private  int qteStock  ;
   @Getter private ProductEtat etat ;
    @Getter private String categorieId;

    public UpdateProductCommand(String id, String nom, double prix, int qteStock, ProductEtat etat, String categorieId) {
        super(id);
        this.nom = nom;
        this.prix = prix;
        this.qteStock = qteStock;
        this.etat = etat;
        this.categorieId = categorieId;
    }
}
