package com.egen.controller;

import com.egen.entity.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/ecommerce/order/create")
public class CustomerOrderController {

    private static final String TOPIC = "createOrder";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    Gson gson = new GsonBuilder().create();

    @PostMapping("/bulk")
    public boolean createOrder(@RequestBody Map<String, Object> custOrders) {

        log.info("Inside createOrder");
        Object arraysItems =  custOrders.get("items");
        Item[] items = gson.fromJson(arraysItems.toString(), Item[].class);
//        kafkaTemplate.send(TOPIC, gson.toJson(mJSONArray)); //Sends as String
        kafkaTemplate.send(new ProducerRecord<>(TOPIC, items));
        return true;
    }
}