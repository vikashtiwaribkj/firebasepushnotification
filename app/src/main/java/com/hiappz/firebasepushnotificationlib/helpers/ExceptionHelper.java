package com.hiappz.firebasepushnotificationlib.helpers;

import com.hiappz.firebasepushnotificationlib.BuildConfig;

import java.io.IOException;

/**
 * Created by aj on 14/9/17.
 */

public class ExceptionHelper {
    private static boolean isBuildDebug = false;

    static {
        if (BuildConfig.DEBUG){
            isBuildDebug = true;
        }else {
            isBuildDebug = false;
        }
    }

    public static void handleNumberFormatException(String TAG, NumberFormatException e){
        LogHelper.e(TAG, "NumberFormatException");
        if (isBuildDebug){
            e.printStackTrace();
        }
    }

    public static void handleInstantiationException(String TAG, InstantiationException e){
        LogHelper.e(TAG, "InstantiationException");
        if (isBuildDebug){
            e.printStackTrace();
        }
    }

    public static void handleIllegalAccessException(String TAG, IllegalAccessException e){
        LogHelper.e(TAG, "IllegalAccessException");
        if (isBuildDebug){
            e.printStackTrace();
        }
    }

    public static void handleClassNotFoundException(String TAG, ClassNotFoundException e){
        LogHelper.e(TAG, "ClassNotFoundException");
        if (isBuildDebug){
            e.printStackTrace();
        }
    }

    public static void handleIOException(String TAG, IOException e){
        LogHelper.e(TAG, "IOException");
        if (isBuildDebug){
            e.printStackTrace();
        }
    }

    public static void handleException(String TAG, Exception e){
        LogHelper.e(TAG, "Exception");
        if (isBuildDebug){
            e.printStackTrace();
        }
    }
}
