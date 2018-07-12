package edu.stanford.nlp.sempre.roboy.utils;

import fig.basic.LogInfo;

public class LogController extends LogInfo {

    private static boolean INFO = true;
    private static boolean WARN = true;

    public static void setLogger(int i){
        if(i==0) {
            INFO=false;
            WARN=false;
            return;
        }
        if(i==1) {
            INFO=false;
            WARN=true ;
            return;
        }
        if(i==2) {
            INFO=true;
            WARN=true;
            return;
        }
        error("Invalid Integer Passed:\t"+i);
    }

    public static boolean isWARN() {
        return WARN;
    }

    public static void setWARN(boolean WARN) {
        LogController.WARN = WARN;
    }

    public static boolean isINFO() {
        return INFO;
    }

    public static void setINFO(boolean INFO) {
        LogController.INFO = INFO;
    }


    public static void begin_track(String format, Object... args) {
        if(INFO)LogInfo.begin_track(format, args);
    }

    public static void begin_track_printAll(String format, Object... args) {
        if(INFO)LogInfo.begin_track_printAll(format, args);
    }

    public static void begin_track_general(Object o, boolean printAllChildLines, boolean printIfParentPrinted) {
        if(INFO)LogInfo.begin_track_general(o, printAllChildLines, printIfParentPrinted);
    }

    public static void end_track() {
        if(INFO)LogInfo.end_track();
    }

    public static <T> T end_track(T x) {
        if(INFO) return LogInfo.end_track(x);
        return null;
    }

    public static void log(Object o) {
        if(INFO)LogInfo.log(o);
    }

    public static void logs(String format, Object... args) {
        if(INFO)LogInfo.logs(format,args);
    }





    public static void dbgs(String format, Object... args) {
        if(INFO) LogInfo.dbgs(format, args);
    }

    public static void dbg(Object o) {

        if(INFO) LogInfo.dbg(o);
    }

    public static void error(Object o) {
//        if(WARN)
            LogInfo.error(o);
    }

    public static void errors(String format, Object... args) {
//        if(WARN)
            LogInfo.errors(format,args);
    }

    public static void warnings(String format, Object... args) {
        if(WARN) LogInfo.warnings(format, args);
    }

    public static void warning(Object o) {
        if(WARN) LogInfo.warning(o);
    }

    public static void fails(String format, Object... args) {
//        if(WARN)
            LogInfo.fails(format, args);
    }

    public static void fail(Object o) {
//        if(WARN)
            LogInfo.fail(o);
    }




}
