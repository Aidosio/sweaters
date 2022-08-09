package com.example.sweaters.Repository;

import com.example.sweaters.Entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<MessageEntity, Integer> {
    List<MessageEntity> findByTag(String tag);
}
