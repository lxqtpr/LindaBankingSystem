package dev.lxqptr.debitcardservice.service;

import dev.lxqptr.debitcardservice.model.dto.DebitCardRequest;
import dev.lxqptr.debitcardservice.model.dto.DebitCardResponse;
import dev.lxqptr.debitcardservice.model.entity.DebitCard;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DebitCardService {

    DebitCardResponse getInfoDebitCard(UUID id);

    DebitCardResponse createDebitCard(DebitCardRequest cardRequest);

    void deleteDebitCard(UUID id);

    void deleteByCustomerId(UUID customerId);

    List<DebitCardResponse> getAllDebitCards();

    Map<UUID, List<DebitCardResponse>> getByCustomerId(UUID customerId);

    DebitCardResponse getByNumberAndExpirationDateAndCvv(String number, String date, String cvv);

    DebitCard getDebitCard(UUID id);

    void deleteAllByCustomerId(UUID customerId);

}
