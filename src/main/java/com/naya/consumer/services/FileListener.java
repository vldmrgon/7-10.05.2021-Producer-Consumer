package com.naya.consumer.services;

import com.naya.common.model.Quote;

import java.io.File;

public interface FileListener {
Quote searchNewFile(String path);
Quote deserializeObject(String path, File file);
}
