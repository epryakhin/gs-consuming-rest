package com.example.consumingrest;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserClientCredsModel {

    @NotNull
    private String login;
    @NotNull
    @Pattern(regexp = "[0-9a-f]+", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String a;
    @NotNull
    @Pattern(regexp = "[0-9a-f]+", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String m1;

}
