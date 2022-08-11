package com.jovan.msaccounts.repositories;

import com.jovan.msaccounts.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<Account, UUID> {

    List<Account> findAllByUserId(UUID userId);

}
