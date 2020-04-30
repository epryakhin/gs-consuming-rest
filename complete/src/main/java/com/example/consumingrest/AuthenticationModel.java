package com.example.consumingrest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthenticationModel {

    @JsonProperty("M2")
    private final String m2;
    private final String x;
    private final String u;
    @JsonProperty("S")
    private final String ss;
    private final Boolean authenticated;
    private final String m1Server;

}
