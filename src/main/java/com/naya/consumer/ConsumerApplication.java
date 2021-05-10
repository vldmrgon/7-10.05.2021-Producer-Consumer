package com.naya.consumer;

import com.naya.common.model.Quote;
import com.naya.consumer.services.FileListener;
import com.naya.consumer.services.FileListenerImpl;
import com.naya.consumer.services.JsonSaver;
import com.naya.consumer.services.JsonSaverImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@EnableScheduling
public class ConsumerApplication {

    String pathToFolderWithFiles = "/home/dev/data/";
    String pathToFolderWithJsonFiles = "/home/dev/json/";
    public static Map<String, File> filesCache = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        log.info("START CONSUMER");
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.naya.consumer");
        ConsumerApplication bean = context.getBean(ConsumerApplication.class);
        bean.startConsumer();

    }

    @SneakyThrows
    @Scheduled(fixedDelay = 10_000)
    public void startConsumer() {

        FileListener fileListener = new FileListenerImpl();
        Quote quote = fileListener.searchNewFile(pathToFolderWithFiles);

        JsonSaver jsonSaver = new JsonSaverImpl();
        jsonSaver.convertAndSave(pathToFolderWithJsonFiles, quote);
    }
}
