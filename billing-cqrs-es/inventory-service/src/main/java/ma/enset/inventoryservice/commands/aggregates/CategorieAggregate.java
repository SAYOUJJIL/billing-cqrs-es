package ma.enset.inventoryservice.commands.aggregates;

import ma.enset.commonapi.commands.CreateCategorieCommand;
import ma.enset.commonapi.commands.UpdateCategorieCommand;
import ma.enset.commonapi.events.CategorieCreatedEvent;
import ma.enset.commonapi.events.CategorieUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class CategorieAggregate {
    @AggregateIdentifier
    private String id;
    private String nom ;
    private String description ;

    public CategorieAggregate() {
    }

    @CommandHandler
    public CategorieAggregate(CreateCategorieCommand command) {
        AggregateLifecycle.apply(
                new CategorieCreatedEvent(
                        command.getId(),
                        command.getNom(),
                        command.getDescription()
                )
        );
    }

    @EventSourcingHandler
    public void on(CategorieCreatedEvent event) {
        this.id = event.getId();
        this.nom = event.getNom();
        this.description = event.getDescription();
    }

    @CommandHandler
    public void handle(UpdateCategorieCommand command) {
        AggregateLifecycle.apply(
                new CategorieUpdatedEvent(
                        command.getId(),
                        command.getNom(),
                        command.getDescription()
                )
        );
    }

    @EventSourcingHandler
    public void on(CategorieUpdatedEvent event) {
        this.id = event.getId();
        this.nom = event.getNom();
        this.description = event.getDescription();
    }

}
