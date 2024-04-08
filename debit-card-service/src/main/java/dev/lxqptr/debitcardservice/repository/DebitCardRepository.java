package dev.lxqptr.debitcardservice.repository;

import dev.lxqptr.debitcardservice.model.entity.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DebitCardRepository extends JpaRepository<DebitCard, UUID> {

    Optional<DebitCard> findByNumberAndExpirationDateAndCvv(String number, String date, String cvv);

    List<DebitCard> findByCustomerId(
            @Param("customerId") UUID customerId);

    void deleteByCustomerId(
            @Param("customerId") UUID customerId);

}
