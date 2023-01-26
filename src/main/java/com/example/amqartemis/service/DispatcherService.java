package com.example.amqartemis.service;

import com.example.amqartemis.dto.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class DispatcherService {

    private final JmsTemplate jmsTemplate;

    @Value("${jms.queue}")
    private String jmsQueue;

    public void sendMessage(ProductDTO product) {
        log.info("Sending message to queue: " + product);
        jmsTemplate.send(jmsQueue, s -> {
            TextMessage textMsg = null;
            try {
                textMsg = s.createTextMessage(new ObjectMapper().writeValueAsString(product));
                textMsg.setJMSType(ProductDTO.class.getTypeName());
                textMsg.setStringProperty("mytypeid", ProductDTO.class.getTypeName());
            } catch (JsonProcessingException e) {
               log.error(e.getMessage());
            }
            return textMsg;
        });
    }
}
