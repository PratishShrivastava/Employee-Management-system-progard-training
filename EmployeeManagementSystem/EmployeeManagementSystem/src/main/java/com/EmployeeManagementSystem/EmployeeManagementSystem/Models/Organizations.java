package com.EmployeeManagementSystem.EmployeeManagementSystem.Models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Organizations")
public class Organizations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int organizationRegistrationNumber;
    @Column(nullable = false)
    private String organizationName;
    @Column(nullable = false)
    private String organizationAddress;
    @Column(nullable = false)
    private long organizationPhoneNumber;
    @Column(nullable = false)
    private String organizationEmail;
}