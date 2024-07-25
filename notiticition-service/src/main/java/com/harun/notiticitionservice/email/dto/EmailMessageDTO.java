package com.harun.notiticitionservice.email.dto;

import lombok.Data;

@Data
public class EmailMessageDTO {
    private String from;
    private String to;
    private String subject;
    private String body;
}
