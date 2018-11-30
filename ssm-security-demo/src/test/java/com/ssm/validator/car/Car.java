package com.ssm.validator.car;

import com.ssm.validator.annotation.ValidPart;

import java.util.HashSet;
import java.util.Set;

//tag::include[]
public class Car {

    private Set<@ValidPart String> parts = new HashSet<>();

    public void addPart(String part) {
        parts.add(part);
    }


}