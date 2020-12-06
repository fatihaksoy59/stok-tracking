package com.faksoy.stocktracking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "store_rooms", indexes = {@Index(name = "idx_srname", columnList = "srname")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreRoom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "srname", length = 100, unique = true)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "block", length = 10)
    private String block;

}
