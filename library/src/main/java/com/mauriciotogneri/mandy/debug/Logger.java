package com.mauriciotogneri.mandy.debug;

import android.util.Log;

public final class Logger
{
    private static final boolean ENABLE_LOGS = true;

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

    private static void log(LogLevel level, String tag, String message, Throwable exception)
    {
        if (ENABLE_LOGS)
        {
            // TODO: log in a file
        }
    }

    // ============================ VERBOSE ============================ \\

    public static void verbose(Object tag, Object message, Throwable exception)
    {
        if (ENABLE_LOGS)
        {
            Log.v(tag.toString(), message.toString(), exception);
        }

        Logger.log(LogLevel.VERBOSE, tag.toString(), message.toString(), exception);
    }

    public static void verbose(Object tag, Object message)
    {
        if (ENABLE_LOGS)
        {
            Log.v(tag.toString(), message.toString());
        }

        Logger.log(LogLevel.VERBOSE, tag.toString(), message.toString(), null);
    }

    public static void verbose(Object tag, Throwable exception)
    {
        if (ENABLE_LOGS)
        {
            Log.v(tag.toString(), exception.getMessage(), exception);
        }

        Logger.log(LogLevel.VERBOSE, tag.toString(), exception.getMessage(), exception);
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

    public static void verbose(Throwable exception)
    {
        String tag = Logger.getDefaultTag();

        if (ENABLE_LOGS)
        {
            Log.v(tag, exception.getMessage(), exception);
        }

        Logger.log(LogLevel.VERBOSE, tag, exception.getMessage(), exception);
    }

    // ============================ DEBUG ============================ \\

    public static void debug(Object tag, Object message, Throwable exception)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.d(tag.toString(), message.toString(), exception);
            }

            Logger.log(LogLevel.DEBUG, tag.toString(), message.toString(), exception);
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

    public static void debug(Object tag, Throwable exception)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.d(tag.toString(), exception.getMessage(), exception);
            }

            Logger.log(LogLevel.DEBUG, tag.toString(), exception.getMessage(), exception);
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

    public static void debug(Throwable exception)
    {
        try
        {
            String tag = Logger.getDefaultTag();

            if (ENABLE_LOGS)
            {
                Log.d(tag, exception.getMessage(), exception);
            }

            Logger.log(LogLevel.DEBUG, tag, exception.getMessage(), exception);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    // ============================ INFO ============================ \\

    public static void info(Object tag, Object message, Throwable exception)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.i(tag.toString(), message.toString(), exception);
            }

            Logger.log(LogLevel.INFO, tag.toString(), message.toString(), exception);
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

    public static void info(Object tag, Throwable exception)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.i(tag.toString(), exception.getMessage(), exception);
            }

            Logger.log(LogLevel.INFO, tag.toString(), exception.getMessage(), exception);
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

    public static void info(Throwable exception)
    {
        try
        {
            String tag = Logger.getDefaultTag();

            if (ENABLE_LOGS)
            {
                Log.i(tag, exception.getMessage(), exception);
            }

            Logger.log(LogLevel.INFO, tag, exception.getMessage(), exception);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    // ============================ WARNING ============================ \\

    public static void warning(Object tag, Object message, Throwable exception)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.w(tag.toString(), message.toString(), exception);
            }

            Logger.log(LogLevel.WARNING, tag.toString(), message.toString(), exception);
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

    public static void warning(Object tag, Throwable exception)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.w(tag.toString(), exception.getMessage(), exception);
            }

            Logger.log(LogLevel.WARNING, tag.toString(), exception.getMessage(), exception);
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

    public static void warning(Throwable exception)
    {
        try
        {
            String tag = Logger.getDefaultTag();

            if (ENABLE_LOGS)
            {
                Log.w(tag, exception.getMessage(), exception);
            }

            Logger.log(LogLevel.WARNING, tag, exception.getMessage(), exception);
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    // ============================ ERROR ============================ \\

    public static void error(Object tag, Object message, Throwable exception)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.e(tag.toString(), message.toString(), exception);
            }

            Logger.log(LogLevel.ERROR, tag.toString(), message.toString(), exception);
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

    public static void error(Object tag, Throwable exception)
    {
        try
        {
            if (ENABLE_LOGS)
            {
                Log.e(tag.toString(), exception.getMessage(), exception);
            }

            Logger.log(LogLevel.ERROR, tag.toString(), exception.getMessage(), exception);
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

    public static void error(Throwable exception)
    {
        try
        {
            String tag = Logger.getDefaultTag();

            if (ENABLE_LOGS)
            {
                Log.e(tag, exception.getMessage(), exception);
            }

            Logger.log(LogLevel.ERROR, tag, exception.getMessage(), exception);
        }
        catch (Exception e)
        {
            // ignore
        }
    }
}