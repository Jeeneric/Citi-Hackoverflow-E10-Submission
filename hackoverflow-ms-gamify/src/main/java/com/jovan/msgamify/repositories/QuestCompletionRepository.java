package com.jovan.msgamify.repositories;

import com.jovan.msgamify.entities.Quest;
import com.jovan.msgamify.entities.QuestCompletion;
import com.jovan.msgamify.models.QuestCompletionId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestCompletionRepository extends CrudRepository<QuestCompletion, QuestCompletionId> {
}
