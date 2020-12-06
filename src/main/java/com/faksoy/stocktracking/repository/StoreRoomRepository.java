package com.faksoy.stocktracking.repository;

import com.faksoy.stocktracking.entity.StoreRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRoomRepository extends JpaRepository<StoreRoom, Long> {
}
