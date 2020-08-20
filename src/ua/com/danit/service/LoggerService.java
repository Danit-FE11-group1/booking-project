package ua.com.danit.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerService {

    public static void info(String logMessage) {
        writeLog("DEBUG", logMessage);
    }

    public static void error(String logMessage) {
        writeLog("ERROR", logMessage);
    }

    public static void writeLog(String logType, String logMessage) {
        try(Writer fileWriter = new FileWriter("application.log", true)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String date = simpleDateFormat.format(new Date());
            fileWriter.append(date)
                    .append(" ")
                    .append(logType)
                    .append(" ")
                    .append(logMessage)
                    .append('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
