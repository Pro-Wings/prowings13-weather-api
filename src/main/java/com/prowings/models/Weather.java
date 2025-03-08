package com.prowings.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Weather{
    public int id;
    public String main;
    public String description;
    public String icon;
}
