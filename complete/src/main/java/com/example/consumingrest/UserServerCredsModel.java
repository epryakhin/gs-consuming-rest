package com.example.consumingrest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
//@Builder
@NoArgsConstructor
public class UserServerCredsModel {

    private String s;
    @JsonProperty("B")
    private String b;
    @JsonProperty("N")
    private String n;
    private String g;
    @JsonProperty("H")
    private String h;
    private String k;
    @JsonProperty("b")
    private String bb;

}
