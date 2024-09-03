package org.training.turkcell.order.rest.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Person {
    private String name;
    private String surname;
    private Integer height;
    private Integer weight;
}
