package org.tommap.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tommap.cards.entity.Card;

import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByMobileNumber(String mobileNumber);
    Optional<Card> findByCardNumber(String cardNumber);
}
