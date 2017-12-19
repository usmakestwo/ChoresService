package com.usmakestwo.chores.handler;

import io.undertow.server.HttpServerExchange;

import java.util.Deque;

public class Helper {
    private Helper() {
        throw new AssertionError();
    }

    static int getChoreId(HttpServerExchange exchange) {
        Deque<String> values = exchange.getQueryParameters().get("choreID");
        if (values == null) {
            return 0;
        }

        String textValue = values.peekFirst();

        if (textValue == null) {
            return 0;
        }

        try {
            return Integer.parseInt(textValue);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    static String getChoreName(HttpServerExchange exchange) {
        Deque<String> values = exchange.getQueryParameters().get("name");

        if (values == null) {
            return null;
        }

        String textValue = values.peekFirst();
        if (textValue == null) {
            return null;
        }
        return textValue;
    }

    static String getChoreRepeat(HttpServerExchange exchange) {
        Deque<String> values = exchange.getQueryParameters().get("recurrent");

        if (values == null) {
            return null;
        }

        String textValue = values.peekFirst();
        if (textValue == null) {
            return null;
        }
        return textValue;
    }

    static String getChoreCompleted(HttpServerExchange exchange) {
        Deque<String> values = exchange.getQueryParameters().get("completed");

        if (values == null) {
            return null;
        }

        String textValue = values.peekFirst();
        if (textValue == null) {
            return null;
        }
        return textValue;
    }

    static String isNull(String s) {
        if (s == null)
            return "";
        else return s;
    }

}
