package ma.enset.inventoryservice.query.repositories;

import ma.enset.inventoryservice.query.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, String> {
}