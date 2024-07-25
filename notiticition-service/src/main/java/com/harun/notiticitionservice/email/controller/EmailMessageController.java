package com.harun.notiticitionservice.email.controller;

import com.harun.notiticitionservice.email.dto.EmailMessageDTO;
import com.harun.notiticitionservice.email.service.EmailMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
@RestController
@RequestMapping("/api/v1/email-messages")
public class EmailMessageController {
    private final EmailMessageService emailMessageService;

    @PostMapping
    public void create(@RequestBody EmailMessageDTO emailMessageDTO) {
        emailMessageService.create(emailMessageDTO);
    }
}
