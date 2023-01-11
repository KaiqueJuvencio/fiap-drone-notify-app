package com.gmail.caioa.morais.scjNotifyApp.service.interfaces;

import com.gmail.caioa.morais.scjNotifyApp.entity.EmailDetalhes;

public interface IEmailService {

    String sendSimpleMail(EmailDetalhes detalhes);

    String sendMailComAnexo(EmailDetalhes detalhes);
}
