package ru.chenko.quotes.model;

import org.springframework.util.StringUtils;

public class QuoteValidator {

    public static void checkQuote(Quote quote) {
        if(QuoteValidator.isEmpty(quote)) {
            throw new RuntimeException("Quote object can't have empty fields");
        }
        if(quote.getBid() != null && quote.getBid() >= quote.getAsk()) {
            throw new RuntimeException("Invalid values of Bid and Ask");
        }
        if(quote.getIsin().length() != 12) {
            throw new RuntimeException("Invalid length of ISIN");
        }
    }

    private static boolean isEmpty(Quote quote) {
        return StringUtils.isEmpty(quote.getIsin()) || quote.getAsk() == null;
    }
}
