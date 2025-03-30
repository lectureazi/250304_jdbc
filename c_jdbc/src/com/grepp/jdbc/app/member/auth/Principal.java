package com.grepp.jdbc.app.member.auth;

import com.grepp.jdbc.app.member.code.Grade;
import java.time.LocalDateTime;

public record Principal(
    String userId,
    Grade grade,
    LocalDateTime loginTime
) {
    
    public static final Principal ANONYMOUS = new Principal(
        "anonymous", Grade.ANONYMOUS, LocalDateTime.now()
    );
}
