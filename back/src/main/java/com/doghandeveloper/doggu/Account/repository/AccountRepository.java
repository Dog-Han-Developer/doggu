package com.doghandeveloper.doggu.Account.repository;

import com.doghandeveloper.doggu.Account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
