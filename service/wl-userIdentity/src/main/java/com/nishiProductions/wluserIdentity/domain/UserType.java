package com.nishiProductions.wluserIdentity.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_type")
@Data
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_type_id")
    private Long userTypeId;
    @Column(name = "user_type_desc")
    private String userTypeDesc;
}
