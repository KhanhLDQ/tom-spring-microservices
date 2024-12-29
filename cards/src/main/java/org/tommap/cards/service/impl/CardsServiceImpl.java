package org.tommap.cards.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tommap.cards.dto.CardsDTO;
import org.tommap.cards.entity.Card;
import org.tommap.cards.exception.CardAlreadyExistsException;
import org.tommap.cards.exception.ResourceNotFoundException;
import org.tommap.cards.mapper.CardsMapper;
import org.tommap.cards.repository.CardsRepository;
import org.tommap.cards.service.ICardsService;

import java.util.Optional;
import java.util.Random;

import static org.tommap.cards.constants.CardsConstants.CREDIT_CARD;
import static org.tommap.cards.constants.CardsConstants.NEW_CARD_LIMIT;

@Service
@RequiredArgsConstructor
public class CardsServiceImpl implements ICardsService {
    private final CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Card> optionalCard = cardsRepository.findByMobileNumber(mobileNumber);
        if (optionalCard.isPresent()) {
            throw new CardAlreadyExistsException(
                    "Card already registered with given mobileNumber" + mobileNumber
            );
        }

        cardsRepository.save(createNewCard(mobileNumber));
    }

    @Override
    public CardsDTO fetchCard(String mobileNumber) {
        Card card = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));

        return CardsMapper.toCardsDTO(card, new CardsDTO());
    }

    @Override
    public boolean updateCard(CardsDTO cardsDTO) {
        Card card = cardsRepository.findByCardNumber(cardsDTO.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card", "cardNumber", cardsDTO.getCardNumber()));

        CardsMapper.toCard(cardsDTO, card);
        cardsRepository.save(card);

        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));

        cardsRepository.deleteById(card.getId());
        return true;
    }

    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CREDIT_CARD);
        newCard.setTotalLimit(NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(NEW_CARD_LIMIT);

        return newCard;
    }
}
