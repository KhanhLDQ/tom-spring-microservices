package org.tommap.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tommap.accounts.entity.Account;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {
}
