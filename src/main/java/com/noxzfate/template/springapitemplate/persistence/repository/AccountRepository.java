package com.noxzfate.template.springapitemplate.persistence.repository;

import com.noxzfate.template.springapitemplate.persistence.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByUsername(String s);

    Optional<AccountEntity> findByUsernameAndPassword(String username, String password);
}
