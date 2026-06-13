package com.compraventap.repository.nosql;

import com.compraventap.model.nosql.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    List<ChatRoom> findByCompradorIdOrVendedorId(Integer compradorId, Integer vendedorId);
}
