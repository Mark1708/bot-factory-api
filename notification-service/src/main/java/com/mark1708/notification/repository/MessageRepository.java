package com.mark1708.notification.repository;

import com.mark1708.notification.model.entity.Message;
import com.mark1708.notification.model.entity.Newsletter;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

  List<Message> findAllByNewsletter(Newsletter newsletter);
}
