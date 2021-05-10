package com.naya.producer.services;

import com.naya.common.model.Quote;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface QuoteSaver {

    String createFile(String path) throws IOException;

    String getCurrentDateAndTime();

    void serializeObject(String path, Quote object) throws IOException;
}
