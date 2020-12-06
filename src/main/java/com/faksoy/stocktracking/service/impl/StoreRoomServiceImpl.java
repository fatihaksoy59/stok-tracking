package com.faksoy.stocktracking.service.impl;


import com.faksoy.stocktracking.entity.StoreRoom;
import com.faksoy.stocktracking.repository.StoreRoomRepository;
import com.faksoy.stocktracking.service.StoreRoomService;
import com.faksoy.stocktracking.util.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class StoreRoomServiceImpl implements StoreRoomService {

    private StoreRoomRepository storeRoomRepository;

    @Override
    public StoreRoom save(StoreRoom storeRoom) {
        StoreRoom st = null;
        st = storeRoomRepository.findByName(storeRoom.getName());
        if (st == null) {
            storeRoom = setLocalDatas(storeRoom);
            return storeRoomRepository.save(storeRoom);
        } else
            throw new RuntimeException("Bu depo daha önce kaydedilmiş.");
    }

    private StoreRoom setLocalDatas(StoreRoom st) {
        User userForSession = UserUtils.getUser();

        st.setCreatedAt(new Date(System.currentTimeMillis()));
        st.setCreatedBy(userForSession.getUsername());
        st.setStatus(true);

        return st;
    }
}
