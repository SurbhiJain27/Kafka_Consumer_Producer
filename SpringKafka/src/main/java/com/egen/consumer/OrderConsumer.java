package com.egen.consumer;

import com.egen.entity.Item;
import com.egen.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class OrderConsumer {

    @Autowired
    private ItemRepository itemRepository;

    //Annotation required to listen/read the message from Kafka server
    @KafkaListener(topics = "createOrder", groupId = "id", containerFactory = "kafkaListenerContainerFactory")
    public void consumeOrders(Item[] products) {
        log.info(" New Entry:  " + products.length);

        if(products!= null && products.length>0){

            Arrays.stream(products).forEach(System.out::println);

            System.out.println("**************************");

            Arrays.stream(products).forEach(item -> System.out.println(item.getItemID()));

            Arrays.stream(products).forEach(item -> itemRepository.save(item));
        }

        log.info(" Order has been preserved in Database");
    }
}