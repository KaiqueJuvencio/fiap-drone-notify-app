package com.gmail.caioa.morais.scjNotifyApp.mensageria.kafka.consumer;

import com.gmail.caioa.morais.scjNotifyApp.entity.Drone;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DroneConsumer {

    @Value("${topic.name.consumer}")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consumeMessage(ConsumerRecord<String, Object> payload){
        try{
            Drone drone = (Drone) payload.value();
            log.info("##### Recebendo dados de coleta...");
            log.info("###Tópico : {}", topicName);
            log.info("### Drone ID: {}", drone.getIdDrone());
            log.info("### Lat/Lon : {}/{}", drone.getLatitude(), drone.getLongitude());
            log.info("### Temperatura : {}", drone.getTemperatura());
            log.info("### % de umidade: {}", drone.getUmidade());
            log.info("### Rastreamento Ativo?: {}", drone.isRastreamentoAtivo());
            log.info("###############");
        } catch (Exception e) {
            log.info("xiiii");
        }

    }
}
