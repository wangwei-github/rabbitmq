package com.rabbit.rabbitmq;

import java.util.HashMap;

public class Tests {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("33", 133);
        map.put("313", 1313);
        System.out.println(map.get("33"));
        System.out.println(map.get("313"));
    }
}
