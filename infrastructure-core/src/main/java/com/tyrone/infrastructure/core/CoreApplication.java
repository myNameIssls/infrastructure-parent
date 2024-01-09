package com.tyrone.infrastructure.core;

import com.tyrone.infrastructure.core.test.Test;

public class CoreApplication {

    public static void main(String[] args) {
        Test test = new Test("123", "二哈");
        System.out.printf(test.toString());
    }

}
