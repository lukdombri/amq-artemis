package com.example.amqartemis.service;

import com.example.amqartemis.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ReceiverService {

    @JmsListener(destination = "${jms.queue}", containerFactory = "productFactory")
    public void receiveMessage(ProductDTO product){
      log.info("Message read: " + product);
    }
}
