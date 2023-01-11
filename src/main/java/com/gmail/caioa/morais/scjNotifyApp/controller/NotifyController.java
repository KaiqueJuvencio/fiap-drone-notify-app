package com.gmail.caioa.morais.scjNotifyApp.controller;

import com.gmail.caioa.morais.scjNotifyApp.entity.EmailDetalhes;
import com.gmail.caioa.morais.scjNotifyApp.service.interfaces.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/notify")
public class NotifyController {

    @Autowired
    private IEmailService emailService;

    @PostMapping("/mail")
    public String sendMail(@RequestBody EmailDetalhes detalhes){
        String status = emailService.sendSimpleMail(detalhes);
        return status;
    }

}
