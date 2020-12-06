package com.faksoy.stocktracking.api;


import com.faksoy.stocktracking.entity.StoreRoom;
import com.faksoy.stocktracking.service.impl.StoreRoomServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("storeroom")
@AllArgsConstructor
public class StoreRoomController {

    private StoreRoomServiceImpl storeRoomServiceImpl;

    @PostMapping
    public ResponseEntity<StoreRoom> createStoreRoom(@Valid @RequestBody StoreRoom storeRoom) {
        return ResponseEntity.ok(storeRoomServiceImpl.save(storeRoom));
    }
}
