package ma.enset.inventoryservice.commands.controllers;

import lombok.AllArgsConstructor;
import ma.enset.commonapi.commands.CreateCategorieCommand;
import ma.enset.commonapi.commands.CreateProductCommand;
import ma.enset.commonapi.commands.UpdateCategorieCommand;
import ma.enset.commonapi.commands.UpdateProductCommand;
import ma.enset.commonapi.dtos.CreateCategorieRequestDTO;
import ma.enset.commonapi.dtos.CreateProductRequestDTO;
import ma.enset.commonapi.dtos.UpdateCategorieRequestDTO;
import ma.enset.commonapi.dtos.UpdateProductRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/product/commands")
@AllArgsConstructor
@CrossOrigin("*")
public class ProductCommandController {
    private final CommandGateway commandGateway;
    private final EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> create(@RequestBody CreateProductRequestDTO request) {
        return commandGateway.send(new CreateProductCommand(
                UUID.randomUUID().toString(),
                request.getNom(),
                request.getPrix(),
                request.getQteStock(),
                request.getEtat(),
                request.getCategorieId()
        ));
    }

    @PutMapping("/update")
    public CompletableFuture<String> update(@RequestBody UpdateProductRequestDTO request) {
        return commandGateway.send(new UpdateProductCommand(
                request.getId(),
                request.getNom(),
                request.getPrix(),
                request.getQteStock(),
                request.getEtat(),
                request.getCategorieId()
        ));
    }

    @GetMapping("/eventStore/{id}")
    public Stream getEventStore(@PathVariable String id) {
        return eventStore.readEvents(id).asStream();
    }
}
