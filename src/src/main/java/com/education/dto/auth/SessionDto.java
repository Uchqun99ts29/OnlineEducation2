package com.education.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class SessionDto {


    private Long accessTokenExpiry;

    private Long refreshTokenExpiry;

    private Long issuedAt;

    private String accessToken;

    private String refreshToken;
}
