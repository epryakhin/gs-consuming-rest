package com.example.consumingrest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
public class AuthenticationModel {

    @JsonProperty("M2")
    private String m2;
    private String x;
    private String u;
    @JsonProperty("S")
    private String ss;
    private Boolean authenticated;
    private String m1Server;

}
