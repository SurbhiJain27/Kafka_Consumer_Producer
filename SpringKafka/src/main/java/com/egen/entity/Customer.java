package com.egen.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Customer {
    int customerId;
    String firstName;
    String lastName;
    String email;
    CustomerOrder customerOrder ;
    LocalDateTime createdDate;
    String createdBy;
    LocalDateTime modifiedDate;
    String modifiedBy;
}
