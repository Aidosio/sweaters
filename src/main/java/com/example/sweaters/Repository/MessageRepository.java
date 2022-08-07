package com.example.sweaters.Repository;

import com.example.sweaters.Entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<MessageEntity, Integer> {
}
