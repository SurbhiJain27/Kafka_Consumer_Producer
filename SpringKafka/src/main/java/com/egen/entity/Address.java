package com.egen.entity;

import lombok.Data;

@Data
public class Address {
    long addressId;
    String addressLineOne;
    String addressLineTwo;
    String city;
    String state;
    String postcode;

}
