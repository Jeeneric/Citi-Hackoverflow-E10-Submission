package com.jovan.msgamify.repositories;

import com.jovan.msgamify.entities.GameProfile;
import com.jovan.msgamify.entities.Quest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestRepository extends CrudRepository<Quest, UUID> {
}
