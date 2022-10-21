package com.tabadul.tabanews.util.registration_util;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String username;
    private final String mobileNo;
    private final String email;
    private final String password;
}
