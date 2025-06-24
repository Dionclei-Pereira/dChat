package me.dionclei.dchat.utils;

import java.util.Arrays;

/*
 * This class can be used to create a default id pattern
 */
public class ContactIdGenerator {
	
    public static String generateId(String from, String to) {
        String[] names = { from, to };
        Arrays.sort(names);
        String id = names[0] + "-" + names[1];
        return id;
    }
	
}
