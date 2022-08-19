package com.EmployeeManagementSystem.EmployeeManagementSystem.Models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Assets")
public class Assets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int assetId;
    @Column(nullable = false)
    private String assetName;
    @Column(nullable = false)
    private int assetCurrentPrice;
    @Column(nullable = false)
    private int assetPurchasedPrice;
}
