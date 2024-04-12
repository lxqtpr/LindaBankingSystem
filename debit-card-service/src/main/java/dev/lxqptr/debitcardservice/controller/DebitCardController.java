package dev.lxqptr.debitcardservice.controller;

import dev.lxqptr.debitcardservice.model.dto.DebitCardRequest;
import dev.lxqptr.debitcardservice.model.dto.DebitCardResponse;
import dev.lxqptr.debitcardservice.service.DebitCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/debit-cards")
public class DebitCardController {

    private final DebitCardService cardService;

    @GetMapping
    public ResponseEntity<List<DebitCardResponse>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllDebitCards());
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<DebitCardResponse> getCardInfo(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(cardService.getInfoDebitCard(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Map<UUID, List<DebitCardResponse>>> getAllByCustomerId(
            @PathVariable UUID customerId
    ) {
        Map<UUID, List<DebitCardResponse>> cards = cardService.getByCustomerId(customerId);
        return ResponseEntity.ok(cards);
    }

    @PostMapping("/create")
    public ResponseEntity<DebitCardResponse> createCard(
            @RequestBody @Valid DebitCardRequest cardRequest
    ) {
        return ResponseEntity.ok(cardService.createDebitCard(cardRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCard(
            @PathVariable UUID id
    ) {
        cardService.deleteDebitCard(id);
        return ResponseEntity.ok("Card with id: " + id + " was blocked");
    }

    @DeleteMapping("/customer/{customerId}")
    ResponseEntity<String> deleteAllByCustomerId(
            @PathVariable UUID customerId
    ) {
        cardService.deleteAllByCustomerId(customerId);
        return ResponseEntity.ok("All customer with id: " + customerId + " debit cards was blocked");
    }

}