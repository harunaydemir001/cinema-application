package com.harun.notiticitionservice.email.mapper;

import com.harun.notiticitionservice.email.dto.EmailMessageDTO;
import com.harun.notiticitionservice.email.entity.EmailMessageEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MapperGenerator {

//    @Mapping(target = "id", ignore = true)
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    EmailMessageDTO emailMessageEntityToEmailMessageDto(EmailMessageEntity emailMessageEntity);

    EmailMessageEntity emailMessageDtoToEmailMessageEntity(EmailMessageDTO emailMessageDTO);

}