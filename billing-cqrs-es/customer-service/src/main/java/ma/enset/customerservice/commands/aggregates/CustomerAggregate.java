package ma.enset.customerservice.commands.aggregates;

import ma.enset.commonapi.commands.CreateCustomerCommand;
import ma.enset.commonapi.commands.UpdateCustomerCommand;
import ma.enset.commonapi.events.CustomerCreatedEvent;
import ma.enset.commonapi.events.CustomerUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class CustomerAggregate {
    @AggregateIdentifier
    private String id ;
    private String nom ;
    private String adresse ;
    private  String email  ;
    private String tel ;

    public CustomerAggregate() {
    }

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command) {
        if (command.getEmail().length() < 6) {
            throw new RuntimeException("Invalid email");
        }

        AggregateLifecycle.apply(new CustomerCreatedEvent(
                command.getId(),
                command.getNom(),
                command.getAdresse(),
                command.getEmail(),
                command.getTel()
        ));
    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent event) {
        this.id = event.getId();
        this.nom = event.getNom();
        this.adresse = event.getAdresse();
        this.email = event.getEmail();
        this.tel = event.getTel();
    }

    @CommandHandler
    public void handle(UpdateCustomerCommand command) {
        AggregateLifecycle.apply(new CustomerUpdatedEvent(
                command.getId(),
                command.getNom(),
                command.getAdresse(),
                command.getEmail(),
                command.getTel()
        ));
    }

    @EventSourcingHandler
    public void on(CustomerUpdatedEvent event) {
        this.nom = event.getNom();
        this.adresse = event.getAdresse();
        this.email = event.getEmail();
        this.tel = event.getTel();
    }
}
