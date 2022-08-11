package com.jovan.msgamify.repositories;

import com.jovan.msgamify.entities.QuestCompletion;
import com.jovan.msgamify.entities.Reward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RewardRepository extends CrudRepository<Reward, UUID> {

    List<Reward> findAllByRewardIdIn(List<UUID> rewardIds);

}
