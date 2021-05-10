package com.naya.producer.services;

import com.naya.common.model.Quote;
import com.naya.common.model.StatusQuote;
import com.naya.producer.ProducerApplication;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class QuoteGeneratorImpl implements QuoteGenerator {

    @Override
    public Quote createQuote() {
        Quote quote = Quote.builder()
                .id(generatorId())
                .text(createRandomNotice())
                .statusQuote(messageStatus(createRandomNotice().length()))
                .build();
        log.info("PRODUCER: Was created " + quote.toString());
        return quote;
    }

    @Override
    public int generatorId() {
        return ProducerApplication.id.getAndIncrement();
    }

    @Override
    public String createRandomNotice() {
        int MIN_NOTICE_LENGTH = 5;
        int MAX_NOTICE_LENGTH = 50;
        return new Random()
                .ints(97, 122 + 1)
                .limit(MIN_NOTICE_LENGTH + new Random().nextInt(MAX_NOTICE_LENGTH - MIN_NOTICE_LENGTH))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public StatusQuote messageStatus(int lengthQuote) {
        if (lengthQuote <= 10) return StatusQuote.SHORT;
        if (lengthQuote > 10 && lengthQuote <= 20) return StatusQuote.MIDDLE;
        if (lengthQuote > 20) return StatusQuote.LONG;
        throw new IllegalArgumentException("Not supported StatusQuote");
    }
}