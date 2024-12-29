package org.tommap.cards.mapper;

import org.tommap.cards.dto.CardsDTO;
import org.tommap.cards.entity.Card;

public class CardsMapper {
    public static CardsDTO toCardsDTO(Card card, CardsDTO cardsDTO) {
        cardsDTO.setCardNumber(card.getCardNumber());
        cardsDTO.setCardType(card.getCardType());
        cardsDTO.setMobileNumber(card.getMobileNumber());
        cardsDTO.setTotalLimit(card.getTotalLimit());
        cardsDTO.setAvailableAmount(card.getAvailableAmount());
        cardsDTO.setAmountUsed(card.getAmountUsed());

        return cardsDTO;
    }

    public static Card toCard(CardsDTO cardDTO, Card card) {
        card.setCardNumber(cardDTO.getCardNumber());
        card.setCardType(cardDTO.getCardType());
        card.setMobileNumber(cardDTO.getMobileNumber());
        card.setTotalLimit(cardDTO.getTotalLimit());
        card.setAvailableAmount(cardDTO.getAvailableAmount());
        card.setAmountUsed(cardDTO.getAmountUsed());

        return card;
    }
}
