package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.*;

public class Log {
    public Logger logger;
    FileHandler fileHandler;
    public Log(String file_name, Level lvl) throws SecurityException, IOException {
        File file = new File(file_name);
        fileHandler = new FileHandler(file_name, true);
        logger = Logger.getLogger(file_name);
        logger.addHandler(fileHandler);
        logger.setLevel(lvl);
        SimpleFormatter formatter = new SimpleFormatter() {
//        SimpleFormatter formatter = new SimpleFormatter();

            //[%1$tF %1$tT] - YYYY-MM-DD HH:MM:SS
            //[%2$-7s] - представляет строку в 7 символов с выравниванием по левому краю
            //%3$s - вывод аргумента как строку
            //%1, %2, %3 ... - номер аргумента

            public static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

            @Override
            public synchronized String format(LogRecord lr) {
                return String.format(format,
                        new Date(lr.getMillis()),
                        lr.getLevel().getName(),
                        lr.getMessage());
            }
        };

        fileHandler.setFormatter(formatter);
    }
}
