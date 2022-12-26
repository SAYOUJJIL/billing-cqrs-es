package ma.enset.inventoryservice.query.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.commonapi.enums.ProductEtat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Id
    private String id ;
    private String nom ;
    private double prix ;
    private  int qte  ;
    private ProductEtat etat ;


    @ManyToOne
    private Categorie categorie;

}
