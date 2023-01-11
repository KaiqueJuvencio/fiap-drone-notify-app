package com.gmail.caioa.morais.scjNotifyApp.entity;


import com.gmail.caioa.morais.scjNotifyApp.controller.dto.DroneDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Data
@ToString
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Drone implements Serializable{
    @Id
    private String idDrone;
    private double latitude;
    private double longitude;
    private double temperatura;
    private double umidade;
    private boolean isRastreamentoAtivo;

}
