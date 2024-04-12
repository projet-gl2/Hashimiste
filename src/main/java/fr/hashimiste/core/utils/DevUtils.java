package fr.hashimiste.core.utils;

import fr.hashimiste.impl.Main;

public class DevUtils {
    public static void debug(String message) {
        if (!Main.DEVELOPMENT) {
            return;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println(stackTrace[Math.min(2, stackTrace.length - 2)] + " -> " + message);
    }
}
