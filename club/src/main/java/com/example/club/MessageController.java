package com.example.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    Producer producer;

    @PostMapping("/1")
    public ResponseEntity<String> send1(){

        String content = "2014-03-05 10:58:51.1  INFO 45469 --- [           main]";
        String routingKey = "sys.dev.info";

        // send to RabbitMQ
        producer.produce(new Log(content, routingKey));



        return ResponseEntity.ok("OK");
    }



    @PostMapping("/2")
    public ResponseEntity<String> send2(){

        String content = "2017-10-10 10:57:51.10 ERROR in ch.qos.logback.core.joran.spi.Interpreter@4:71";
        String routingKey = "sys.testttetre";

        // send to RabbitMQ
        producer.produce(new Log(content, routingKey));

        return ResponseEntity.ok("OK");
    }

    @PostMapping("/3")
    public ResponseEntity<String> send3(){
        String content = "2017-10-10 10:57:51.112  ERROR java.lang.Exception: java.lang.Exception";
        String routingKey = "app.prod.error";

        // send to RabbitMQ
        producer.produce(new Log(content, routingKey));
        return ResponseEntity.ok("OK");
    }



}