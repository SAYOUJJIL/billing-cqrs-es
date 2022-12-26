package ma.enset.customerservice.commands.controllers;

import lombok.AllArgsConstructor;
import ma.enset.commonapi.commands.CreateCustomerCommand;
import ma.enset.commonapi.commands.UpdateCustomerCommand;
import ma.enset.commonapi.dtos.CreateCustomerRequestDTO;
import ma.enset.commonapi.dtos.UpdateCustomerRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/customer/commands")
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/createCustomer")
    public CompletableFuture<String> createRadar(@RequestBody CreateCustomerRequestDTO request) {
        return commandGateway.send(new CreateCustomerCommand(
                UUID.randomUUID().toString(),
                request.getNom(),
                request.getAdresse(),
                request.getEmail(),
                request.getTel()));
    }

    @PutMapping("/updateCustomer")
    public CompletableFuture<String> updateRadar(@RequestBody UpdateCustomerRequestDTO request) {
        return commandGateway.send(new UpdateCustomerCommand(
                request.getId(),
                request.getNom(),
                request.getAdresse(),
                request.getEmail(),
                request.getTel()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        ResponseEntity<String> entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    @GetMapping("/eventStore/{customerId}")
    public Stream getEventsForAccount(@PathVariable(value = "customerId") String radarId) {
        return eventStore.readEvents(radarId).asStream();
    }
}
