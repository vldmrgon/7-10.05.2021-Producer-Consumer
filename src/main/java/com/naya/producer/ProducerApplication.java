package com.naya.producer;

import com.naya.common.model.Quote;
import com.naya.producer.services.QuoteGenerator;
import com.naya.producer.services.QuoteGeneratorImpl;
import com.naya.producer.services.QuoteSaver;
import com.naya.producer.services.QuoteSaverImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@EnableScheduling
public class ProducerApplication {
    public static AtomicInteger id = new AtomicInteger(1);
    private String library = "/home/dev/data/";

    public static void main(String[] args) {
        log.info("START PRODUCER");
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.naya.producer");
        ProducerApplication bean = context.getBean(ProducerApplication.class);
        bean.startProducer();
    }

    @SneakyThrows
    @Scheduled(fixedDelay = 10_000)
    public void startProducer() {

        QuoteGenerator generatorQuote = new QuoteGeneratorImpl();
        Quote quote = generatorQuote.createQuote();

        QuoteSaver quoteSaver = new QuoteSaverImpl();
        String pathToFile = quoteSaver.createFile(library);
        quoteSaver.serializeObject(pathToFile, quote);

    }
}