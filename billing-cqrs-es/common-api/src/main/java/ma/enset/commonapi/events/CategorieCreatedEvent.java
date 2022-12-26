package ma.enset.commonapi.events;

import lombok.Getter;

public class CategorieCreatedEvent extends BaseEvent<String> {
    @Getter private String nom ;
    @Getter  private String description ;

    public CategorieCreatedEvent(String id, String nom, String description) {
        super(id);
        this.nom = nom;
        this.description = description;
    }
}
