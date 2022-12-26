package ma.enset.inventoryservice.commands.aggregates;

import ma.enset.commonapi.commands.CreateProductCommand;
import ma.enset.commonapi.commands.UpdateProductCommand;
import ma.enset.commonapi.enums.ProductEtat;
import ma.enset.commonapi.events.ProductCreatedEvent;
import ma.enset.commonapi.events.ProductUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String id;
    private String nom ;
    private double prix ;
    private  int qteStock  ;
    private ProductEtat etat ;
    private String categorieId;

    public ProductAggregate() {
    }


    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        AggregateLifecycle.apply(
                new ProductCreatedEvent(
                        command.getId(),
                        command.getNom(),
                        command.getPrix(),
                        command.getQteStock(),
                        command.getEtat(),
                        command.getCategorieId()
                )
        );
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.id = event.getId();
        this.nom = event.getNom();
        this.prix = event.getPrix();
        this.qteStock = event.getQteStock();
        this.etat = event.getEtat();
        this.categorieId = event.getCategorieId();
    }

    @CommandHandler
    public void handle(UpdateProductCommand command) {
        AggregateLifecycle.apply(
                new ProductUpdatedEvent(
                        command.getId(),
                        command.getNom(),
                        command.getPrix(),
                        command.getQteStock(),
                        command.getEtat(),
                        command.getCategorieId()
                )
        );
    }

    @EventSourcingHandler
    public void on(ProductUpdatedEvent event) {
        this.id = event.getId();
        this.nom = event.getNom();
        this.prix = event.getPrix();
        this.qteStock = event.getQteStock();
        this.etat = event.getEtat();
        this.categorieId = event.getCategorieId();
    }
}
