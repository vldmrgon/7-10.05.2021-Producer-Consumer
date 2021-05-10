package com.naya.producer.services;

import com.naya.common.model.Quote;
import com.naya.common.model.StatusQuote;

public interface QuoteGenerator {

    Quote createQuote();

    int generatorId();

    String createRandomNotice();

    StatusQuote messageStatus(int lengthQuote);
}
