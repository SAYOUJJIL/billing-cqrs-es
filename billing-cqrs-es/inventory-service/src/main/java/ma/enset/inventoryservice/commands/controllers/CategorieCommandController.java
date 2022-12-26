package ma.enset.inventoryservice.commands.controllers;

import lombok.AllArgsConstructor;
import ma.enset.commonapi.commands.CreateCategorieCommand;
import ma.enset.commonapi.commands.UpdateCategorieCommand;
import ma.enset.commonapi.dtos.CreateCategorieRequestDTO;
import ma.enset.commonapi.dtos.UpdateCategorieRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/categorie/commands")
@AllArgsConstructor
@CrossOrigin("*")
public class CategorieCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;
    @PostMapping("/create")
    public CompletableFuture<String> createProprietaire(@RequestBody CreateCategorieRequestDTO request) {
        return commandGateway.send(new CreateCategorieCommand(
                UUID.randomUUID().toString(),
                request.getNom(),
                request.getDescription()

        ));
    }

    @PutMapping("/update")
    public CompletableFuture<String> updateProprietaire(@RequestBody UpdateCategorieRequestDTO request) {
        return commandGateway.send(new UpdateCategorieCommand(
                request.getId(),
                request.getNom(),
                request.getDescription()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        ResponseEntity<String> entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    @GetMapping("/eventStore/{id}")
    public Stream getEventStore(@PathVariable String id) {
        return eventStore.readEvents(id).asStream();
    }

}
