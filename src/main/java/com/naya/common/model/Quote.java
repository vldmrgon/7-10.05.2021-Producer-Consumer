package com.naya.common.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Quote implements Serializable {
    private int id;
    private String text;
    private StatusQuote statusQuote;
}
