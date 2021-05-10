package com.naya.consumer.services;

import com.naya.common.model.Quote;

public interface JsonSaver {
    void convertAndSave(String path, Quote object);
}
