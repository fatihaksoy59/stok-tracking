package com.faksoy.stocktracking.service.impl;


import com.faksoy.stocktracking.entity.StoreRoom;
import com.faksoy.stocktracking.repository.StoreRoomRepository;
import com.faksoy.stocktracking.service.StoreRoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StoreRoomServiceImpl implements StoreRoomService {

    private StoreRoomRepository storeRoomRepository;

    @Override
    public StoreRoom save(StoreRoom storeRoom) {
        return storeRoomRepository.save(storeRoom);
    }
}
