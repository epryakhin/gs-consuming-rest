package com.example.consumingrest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserServerCredsModel {

    private final String s;
    @JsonProperty("B")
    private final String b;
    @JsonProperty("N")
    private final String n;
    private final String g;
    @JsonProperty("H")
    private final String h;
    private final String k;
    @JsonProperty("b")
    private final String bb;

}
