package com.naya.consumer.services;

import com.naya.common.model.Quote;
import com.naya.consumer.ConsumerApplication;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class FileListenerImpl implements FileListener {

    @Override
    public Quote searchNewFile(String path) {
        Quote quote = null;

         Set<File> collect = Arrays
                .stream(Objects.requireNonNull(new File(path).listFiles()))
                .collect(Collectors.toSet());

        for (File file : collect) {
            if (!ConsumerApplication.filesCache.containsKey(file.getName())) {
                quote = deserializeObject(path, file);
                break;
            }
        }
        return quote;
    }

    @Override
    @SneakyThrows
    public Quote deserializeObject(String path, File file) {
        ConsumerApplication.filesCache.put(file.getName(), file);
        log.info("CONSUMER: File: " + file.getName() + " was added to Cache");
        ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(path + file.getName()));
        Quote object = (Quote) ois.readObject();
        ois.close();
        return object;
    }
}