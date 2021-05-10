package com.naya.producer.services;

import com.naya.common.model.Quote;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class QuoteSaverImpl implements QuoteSaver {

    @Override
    @SneakyThrows
    public String createFile(String path) {
        String nameFile = path + getCurrentDateAndTime();
        File file = new File(path);
        file.mkdirs();
        file.createNewFile();
        log.info("PRODUCER: Was created a file: " + nameFile);
        return nameFile;
    }

    @Override
    public String getCurrentDateAndTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss");
        return formatter.format(new Date());
    }

    @Override
    @SneakyThrows
    public void serializeObject(String path, Quote object) {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        fos.close();
        log.info("PRODUCER: New " + object.toString() + " was added to file: " + path);
    }
}
