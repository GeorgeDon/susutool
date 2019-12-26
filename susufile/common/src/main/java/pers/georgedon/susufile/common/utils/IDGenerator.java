package pers.georgedon.susufile.common.utils;

import java.util.UUID;

public class IDGenerator {

    public static String createId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
