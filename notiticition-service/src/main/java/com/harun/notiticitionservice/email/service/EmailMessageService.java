package com.harun.notiticitionservice.email.service;

import com.harun.notiticitionservice.email.dto.EmailMessageDTO;
import com.harun.notiticitionservice.email.entity.EmailMessageEntity;
import com.harun.notiticitionservice.email.mapper.MapperGenerator;
import com.harun.notiticitionservice.email.mapper.MapperGeneratorSingleton;
import com.harun.notiticitionservice.email.repository.EmailMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailMessageService {
    private final EmailMessageRepository emailMessageRepository;
    private final EmailMessageSenderService emailMessageSenderService;
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    public void create(EmailMessageDTO emailMessageDTO) {
        EmailMessageEntity emailMessageEntity = mapper.emailMessageDtoToEmailMessageEntity(emailMessageDTO);
        emailMessageRepository.save(emailMessageEntity);
        emailMessageSenderService.sendEmail(emailMessageDTO);
    }
}
