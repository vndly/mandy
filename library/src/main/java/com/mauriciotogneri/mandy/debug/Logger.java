package com.mauriciotogneri.mandy.debug;

import android.util.Log;

import com.mauriciotogneri.mandy.BuildConfig;

public final class Logger
{
    private static OnLog onLog;
    private static final boolean ENABLE_LOGS = BuildConfig.DEBUG;

    public enum LogLevel
    {
        VERBOSE, DEBUG, INFO, WARNING, ERROR
    }

    private Logger()
    {
    }

    private static String getDefaultTag()
    {
        try
        {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            String className = stackTrace[3].getClassName();

            return className.substring(className.lastIndexOf('.') + 1);
        }
        catch (Exception e)
        {
            return Logger.class.getName();
        }
    }

    public static void setOnLog(OnLog callback)
    {
        onLog = callback;
    }

    private static void log(LogLevel level, String tag, String message, Throwable throwable)
    {
        if (onLog != null)
        {
            onLog.onLog(level, tag, message, throwable);
        }
    }

    // ============================ VERBOSE ============================ \\

    public static void verbose(Object tag, Object message, Throwable throwable)
    {
        if (ENABLE_LOGS)
        {
            Log.v(tag.toString(), message.toString(), throwable);
        }

        Logger.log(LogLevel.VERBOSE, tag.toString(), message.toString(), throwable);
    }

    public static void verbose(Object tag, Object message)
    {
        if (ENABLE_LOGS)
        {
            Log.v(tag.toString(), message.toString());
        }

        Logger.log(LogLevel.VERBOSE, tag.toString(), message.toString(), null);
    }

    public static void verbose(Object tag, Throwable throwable)
    {
        if (ENABLE_LOGS)
        {
            Log.v(tag.toString(), throwable.getMessage(), throwable);
        }

        Logger.log(LogLevel.VERBOSE, tag.toString(), throwable.getMessage(), throwable);
    }

    public static void verbose(Object message)
    {
        String tag = Logger.getDefaultTag();

        if (ENABLE_LOGS)
        {
            Log.v(tag, message.toString());
        }

        Logger.log(LogLevel.VERBOSE, tag, message.toString(), null);
    }

    public static void verbose(Throwable throwable)
    {
        String tag = Logger.getDefaultTag();

        if (ENABLE_LOGS)
        {
            Log.v(tag, throwable.getMessage(), throwable);
        }

        Logger.log(LogLevel.VERBOSE, tag, throwable.getMessage(), throwable);
    }

    // ============================ DEBUG ============================ \\

    public static void debug(Object tag, Object message, Throwable throwable)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.d(tag.toString(), message.toString(), throwable);
            }

            Logger.log(LogLevel.DEBUG, tag.toString(), message.toString(), throwable);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void debug(Object tag, Object message)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.d(tag.toString(), message.toString());
            }

            Logger.log(LogLevel.DEBUG, tag.toString(), message.toString(), null);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void debug(Object tag, Throwable throwable)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.d(tag.toString(), throwable.getMessage(), throwable);
            }

            Logger.log(LogLevel.DEBUG, tag.toString(), throwable.getMessage(), throwable);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void debug(Object message)
    {
        try
        {
            String tag = Logger.getDefaultTag();

            if (ENABLE_LOGS)
            {
                Log.d(tag, message.toString());
            }

            Logger.log(LogLevel.DEBUG, tag, message.toString(), null);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void debug(Throwable throwable)
    {
        try
        {
            String tag = Logger.getDefaultTag();

            if (ENABLE_LOGS)
            {
                Log.d(tag, throwable.getMessage(), throwable);
            }

            Logger.log(LogLevel.DEBUG, tag, throwable.getMessage(), throwable);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    // ============================ INFO ============================ \\

    public static void info(Object tag, Object message, Throwable throwable)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.i(tag.toString(), message.toString(), throwable);
            }

            Logger.log(LogLevel.INFO, tag.toString(), message.toString(), throwable);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void info(Object tag, Object message)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.i(tag.toString(), message.toString());
            }

            Logger.log(LogLevel.INFO, tag.toString(), message.toString(), null);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void info(Object tag, Throwable throwable)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.i(tag.toString(), throwable.getMessage(), throwable);
            }

            Logger.log(LogLevel.INFO, tag.toString(), throwable.getMessage(), throwable);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void info(Object message)
    {
        try
        {
            String tag = Logger.getDefaultTag();

            if (ENABLE_LOGS)
            {
                Log.i(tag, message.toString());
            }

            Logger.log(LogLevel.INFO, tag, message.toString(), null);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void info(Throwable throwable)
    {
        try
        {
            String tag = Logger.getDefaultTag();

            if (ENABLE_LOGS)
            {
                Log.i(tag, throwable.getMessage(), throwable);
            }

            Logger.log(LogLevel.INFO, tag, throwable.getMessage(), throwable);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    // ============================ WARNING ============================ \\

    public static void warning(Object tag, Object message, Throwable throwable)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.w(tag.toString(), message.toString(), throwable);
            }

            Logger.log(LogLevel.WARNING, tag.toString(), message.toString(), throwable);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void warning(Object tag, Object message)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.w(tag.toString(), message.toString());
            }

            Logger.log(LogLevel.WARNING, tag.toString(), message.toString(), null);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void warning(Object tag, Throwable throwable)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.w(tag.toString(), throwable.getMessage(), throwable);
            }

            Logger.log(LogLevel.WARNING, tag.toString(), throwable.getMessage(), throwable);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void warning(Object message)
    {
        try
        {
            String tag = Logger.getDefaultTag();

            if (ENABLE_LOGS)
            {
                Log.w(tag, message.toString());
            }

            Logger.log(LogLevel.WARNING, tag, message.toString(), null);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void warning(Throwable throwable)
    {
        try
        {
            String tag = Logger.getDefaultTag();

            if (ENABLE_LOGS)
            {
                Log.w(tag, throwable.getMessage(), throwable);
            }

            Logger.log(LogLevel.WARNING, tag, throwable.getMessage(), throwable);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    // ============================ ERROR ============================ \\

    public static void error(Object tag, Object message, Throwable throwable)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.e(tag.toString(), message.toString(), throwable);
            }

            Logger.log(LogLevel.ERROR, tag.toString(), message.toString(), throwable);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void error(Object tag, Object message)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.e(tag.toString(), message.toString());
            }

            Logger.log(LogLevel.ERROR, tag.toString(), message.toString(), null);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void error(Object tag, Throwable throwable)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.e(tag.toString(), throwable.getMessage(), throwable);
            }

            Logger.log(LogLevel.ERROR, tag.toString(), throwable.getMessage(), throwable);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void error(Object message)
    {
        try
        {
            String tag = Logger.getDefaultTag();

            if (ENABLE_LOGS)
            {
                Log.e(tag, message.toString());
            }

            Logger.log(LogLevel.ERROR, tag, message.toString(), null);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public static void error(Throwable throwable)
    {
        try
        {
            String tag = Logger.getDefaultTag();

            if (ENABLE_LOGS)
            {
                Log.e(tag, throwable.getMessage(), throwable);
            }

            Logger.log(LogLevel.ERROR, tag, throwable.getMessage(), throwable);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public interface OnLog
    {
        void onLog(LogLevel level, String tag, String message, Throwable throwable);
    }
}