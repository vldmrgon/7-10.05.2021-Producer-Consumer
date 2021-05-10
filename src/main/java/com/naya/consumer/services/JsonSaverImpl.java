package com.naya.consumer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naya.common.model.Quote;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class JsonSaverImpl implements JsonSaver {

    @Override
    @SneakyThrows
    public void convertAndSave(String path, Quote object) {
        if (object != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String str = path + System.nanoTime() + ".txt";
            objectMapper.writeValue(new File(str), object);
            log.info("CONSUMER: Object path: " + str + " was converted to json format");
        }
    }
}