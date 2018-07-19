package edu.stanford.nlp.sempre.roboy.utils;

import fig.basic.LogInfo;

import java.io.PrintWriter;

public class NLULoggerController extends LogInfo {

    private static boolean ALL = true;
    private static boolean WARN = true;

    public static void setLogger(String s){
        if(s.toLowerCase().equals("off")) {
            ALL =false;
            WARN=false;
            return;
        }
        if(s.toLowerCase().equals("warn")) {
            ALL =false;
            WARN=true ;
            return;
        }

        if(!s.toLowerCase().equals("info")){
            warning("Invalid Level Passed:\t"+s);
            warning("Please Check Dialog's Config.Properties. Now Defaulting to INFO");
        }
        ALL =true;
        WARN=true;
        return;


    }

    public static boolean isWARN() {
        return WARN;
    }

    public static void setWARN(boolean WARN) {
        NLULoggerController.WARN = WARN;
    }

    public static boolean isALL() {
        return ALL;
    }

    public static void setALL(boolean ALL) {
        NLULoggerController.ALL = ALL;
    }


    public static void begin_track(String format, Object... args) {
        if(ALL)LogInfo.begin_track(format, args);
    }

    public static void begin_track_printAll(String format, Object... args) {
        if(ALL)LogInfo.begin_track_printAll(format, args);
    }

    public static void begin_track_general(Object o, boolean printAllChildLines, boolean printIfParentPrinted) {
        if(ALL)LogInfo.begin_track_general(o, printAllChildLines, printIfParentPrinted);
    }

    public static void end_track() {
        if(ALL)LogInfo.end_track();
    }

    public static <T> T end_track(T x) {
        if(ALL) return LogInfo.end_track(x);
        return null;
    }

    public static void log(Object o) {
        if(ALL)LogInfo.log(o);
    }

    public static void logs(String format, Object... args) {
        if(ALL)LogInfo.logs(format,args);
    }

    public static void setFileOut(PrintWriter newFileOut) {
        if(ALL) LogInfo.setFileOut(newFileOut);
    }



        public static void dbgs(String format, Object... args) {
        if(ALL) LogInfo.dbgs(format, args);
    }

    public static void dbg(Object o) {

        if(ALL) LogInfo.dbg(o);
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
