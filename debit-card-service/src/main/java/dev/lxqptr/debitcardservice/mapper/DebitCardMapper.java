package dev.lxqptr.debitcardservice.mapper;

import dev.lxqptr.debitcardservice.model.dto.DebitCardRequest;
import dev.lxqptr.debitcardservice.model.dto.DebitCardResponse;
import dev.lxqptr.debitcardservice.model.entity.DebitCard;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DebitCardMapper {

    DebitCard toCard(DebitCardResponse cardResponse);

    DebitCard toCard(DebitCardRequest cardRequest);

    DebitCardResponse toResponse(DebitCard card);

    DebitCardRequest toRequest(DebitCard card);

    List<DebitCardResponse> toResponse(List<DebitCard> cards);

}
