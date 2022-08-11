package com.jovan.msaccounts.repositories;

import com.jovan.msaccounts.entities.Destination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DestinationRepository extends CrudRepository<Destination, UUID> {

    List<Destination> findByAccountIdIn(List<UUID> accounts);

}
