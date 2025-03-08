package com.prowings.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Wind{
    public double speed;
    public int deg;
    public double gust;
}
