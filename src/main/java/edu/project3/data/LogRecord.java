package edu.project3.data;

import java.time.LocalDateTime;

public record LogRecord(
    String remoteAddress,
    String remoteUser,
    LocalDateTime dateTime,
    String request,
    int statusCode,
    int bodyBytesSent,
    String referer,
    String userAgent
) {
}
