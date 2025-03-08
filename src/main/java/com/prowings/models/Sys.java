package com.prowings.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Sys{
    public int type;
    public int id;
    public String country;
    public long sunrise;
    public long sunset;
}
