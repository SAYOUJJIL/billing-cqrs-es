package ma.enset.commonapi.events;

import lombok.Getter;

public class CategorieUpdatedEvent extends BaseEvent<String> {
    @Getter private String nom ;
    @Getter  private String description ;

    public CategorieUpdatedEvent(String id, String nom, String description) {
        super(id);
        this.nom = nom;
        this.description = description;
    }
}
