package com.harun.notiticitionservice.email.repository;

import com.harun.notiticitionservice.email.entity.EmailMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailMessageRepository extends JpaRepository<EmailMessageEntity, Long> {

}
