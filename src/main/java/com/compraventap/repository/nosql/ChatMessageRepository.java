package com.compraventap.repository.nosql;

import com.compraventap.model.nosql.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByChatRoomIdOrderByTimestampAsc(String chatRoomId);
}
