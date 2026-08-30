package com.tyrone.infrastructure;

public class Main {

    public static final ThreadLocal<String> userIdHolder = new ThreadLocal<>();

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}