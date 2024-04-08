package dev.lxqptr.debitcardservice.service.impl;

import dev.lxqptr.debitcardservice.exception.DebitCardNotFoundException;
import dev.lxqptr.debitcardservice.exception.InvalidArgumentException;
import dev.lxqptr.debitcardservice.mapper.DebitCardMapper;
import dev.lxqptr.debitcardservice.model.dto.DebitCardRequest;
import dev.lxqptr.debitcardservice.model.dto.DebitCardResponse;
import dev.lxqptr.debitcardservice.model.entity.DebitCard;
import dev.lxqptr.debitcardservice.repository.DebitCardRepository;
import dev.lxqptr.debitcardservice.service.DebitCardService;
import dev.lxqptr.debitcardservice.utils.CvvAlgorithm;
import dev.lxqptr.debitcardservice.utils.DateAlgorithm;
import dev.lxqptr.debitcardservice.utils.NumberAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DebitCardServiceImpl implements DebitCardService {

    private final DebitCardRepository cardRepository;
    private final DebitCardMapper cardMapper;


    @Override
    public DebitCardResponse getInfo(
            final UUID id
    ) {
        DebitCard card = get(id);
        return cardMapper.toResponse(card);
    }

    @Override
    @Transactional
    public DebitCardResponse save(
            final DebitCardRequest cardRequest
    ) {
        DebitCard card = cardMapper.toCard(cardRequest);

        card.setCustomerId(cardRequest.getCustomerId());
        card.setCardHolderName(cardRequest.getCardHolderName());
        card.setNumber(new NumberAlgorithm().generateCardNumber());
        card.setExpirationDate(new DateAlgorithm().generateDate());
        card.setCvv(new CvvAlgorithm().generateCVV());

        if (card == null || card.getCustomerId() == null) {
            throw new InvalidArgumentException("Card or customerId not found.");
        }

        log.info("Saving card with id: {}", card.getId());
        cardRepository.save(card);

        return cardMapper.toResponse(card);
    }

    @Override
    @Transactional
    public void delete(
            final UUID id
    ) {
        cardRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByCustomerId(
            final UUID customerId
    ) {
        cardRepository.deleteByCustomerId(customerId);
    }

    @Override
    public List<DebitCardResponse> getAll() {
        return cardMapper.toResponse(cardRepository.findAll());
    }

    @Override
    public Map<UUID, List<DebitCardResponse>> getByCustomerId(
            final UUID customerId
    ) {
        return cardRepository.findByCustomerId(customerId)
                .stream()
                .collect(Collectors.groupingBy(DebitCard::getCustomerId,
                        Collectors.mapping(card -> new DebitCardResponse(
                                card.getNumber(),
                                card.getExpirationDate(),
                                card.getCvv(),
                                card.getCardHolderName(),
                                card.getBalance()
                        ), Collectors.toList())));
    }

    @Override
    public DebitCardResponse getByNumberAndExpirationDateAndCvv(
            final String number,
            final String date,
            final String cvv
    ) {
        DebitCard existingCard = cardRepository.findByNumberAndExpirationDateAndCvv(number, date, cvv)
                .orElseThrow(DebitCardNotFoundException::new);

        return cardMapper.toResponse(existingCard);
    }

    @Override
    public DebitCard get(
            final UUID id
    ) {
        return cardRepository.findById(id)
                .orElseThrow(DebitCardNotFoundException::new);
    }
}
