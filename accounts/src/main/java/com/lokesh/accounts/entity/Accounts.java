package com.lokesh.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Accounts extends  BaseEntity {
    @Id
    private Long accountNumber; // Not used @GeneratedValue, since we want to generate primary id using logic
    private Long customerId;
    private String accountType;
    private String branchAddress;
}