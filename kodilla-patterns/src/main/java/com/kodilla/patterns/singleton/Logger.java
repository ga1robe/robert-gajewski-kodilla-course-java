package com.kodilla.patterns.singleton;

import java.text.SimpleDateFormat;
import java.util.Date;

public enum Logger {

    INSTANCE;

    private String lastLog = "";

    private String file = "";

    public void log(String log) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        lastLog = timeStamp.concat(" ").concat(log);
        System.out.println("Log: [" + timeStamp.concat(" ").concat(log) + "]");
    }

    public String getLastLog() {
        return lastLog;
    }

    public String getFileName() {
        return "singleton.log";
    }

    public void open(String file) {
        this.file = file;
    }
}
