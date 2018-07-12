package edu.stanford.nlp.sempre.roboy.utils;

import fig.basic.LogInfo;

public class LogController extends LogInfo {

    private static boolean LOGGING = true;
    public static void begin_track(String format, Object... args) {
        if(LOGGING)LogInfo.begin_track(format, args);
    }

    public static void begin_track_printAll(String format, Object... args) {
        if(LOGGING)LogInfo.begin_track_printAll(format, args);
    }

    public static void begin_track_general(Object o, boolean printAllChildLines, boolean printIfParentPrinted) {
        if(LOGGING)LogInfo.begin_track_general(o, printAllChildLines, printIfParentPrinted);
    }

    public static void end_track() {
        if(LOGGING)LogInfo.end_track();
    }

    public static <T> T end_track(T x) {
        if(LOGGING) return LogInfo.end_track(x);
        return null;
    }

    public static void log(Object o) {
        if(LOGGING)LogInfo.log(o);
    }

    public static void logs(String format, Object... args) {
        if(LOGGING)LogInfo.logs(format,args);
    }


    public static boolean isLOGGING() {
        return LOGGING;
    }

    public static void setLOGGING(boolean LOGGING) {
        LogController.LOGGING = LOGGING;
    }
}
