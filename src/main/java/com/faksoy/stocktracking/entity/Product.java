package com.faksoy.stocktracking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "products", indexes = {@Index(name = "idx_productName", columnList = "pname")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "pname", length = 100, unique = true)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "count", length = 1000)
    private int count;

    @Column(name = "unit", length = 1000)
    private String unit;

    @JoinColumn(name = "puser_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name = "storeroom_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private StoreRoom storeRoom;
}
