package com.simple.bank.entity;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseEntity {

    @Temporal(TemporalType.TIME)
    private Date createdDateTime;

    @Temporal(TemporalType.TIME)
    private Date lastUpdatedDateTime;
}
