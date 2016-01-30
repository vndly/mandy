package com.mauriciotogneri.mandy.util;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.mauriciotogneri.mandy.debug.Logger;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ResourceHelper
{
    public static String readFromResource(Resources resources, int resourceId)
    {
        InputStream inputStream;

        try
        {
            inputStream = resources.openRawResource(resourceId);

            return readInputStreamAsString(inputStream);
        }
        catch (Exception e)
        {
            Logger.error(e);

            throw new RuntimeException("Unable to read resource: " + resourceId);
        }
    }

    public static String readFromAssetsAsString(AssetManager assetManager, String path)
    {
        InputStream inputStream;

        try
        {
            inputStream = assetManager.open(path);

            return readInputStreamAsString(inputStream);
        }
        catch (Exception e)
        {
            Logger.error(e);

            throw new RuntimeException("Unable to read assets: " + path);
        }
    }

    public static String[] readFromAssetsAsArray(AssetManager assetManager, String path)
    {
        InputStream inputStream;

        try
        {
            inputStream = assetManager.open(path);

            return readInputStreamAsArray(inputStream);
        }
        catch (Exception e)
        {
            Logger.error(e);

            throw new RuntimeException("Unable to read assets: " + path);
        }
    }

    public static String readInputStreamAsString(InputStream inputStream)
    {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try
        {
            StringBuilder builder = new StringBuilder();

            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            String nextLine;

            while ((nextLine = bufferedReader.readLine()) != null)
            {
                builder.append(nextLine);
                builder.append('\n');
            }

            return builder.toString();
        }
        catch (Exception e)
        {
            Logger.error(e);

            throw new RuntimeException("Unable to read input stream");
        }
        finally
        {
            closeResource(inputStream);
            closeResource(inputStreamReader);
            closeResource(bufferedReader);
        }
    }

    public static String[] readInputStreamAsArray(InputStream inputStream)
    {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try
        {
            List<String> result = new ArrayList<>();

            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            String nextLine;

            while ((nextLine = bufferedReader.readLine()) != null)
            {
                result.add(nextLine);
            }

            return result.toArray(new String[result.size()]);
        }
        catch (Exception e)
        {
            Logger.error(e);

            throw new RuntimeException("Unable to read input stream");
        }
        finally
        {
            closeResource(inputStream);
            closeResource(inputStreamReader);
            closeResource(bufferedReader);
        }
    }

    public static void closeResource(Closeable resource)
    {
        if (resource != null)
        {
            try
            {
                resource.close();
            }
            catch (Exception e)
            {
                Logger.error(e);
            }
        }
    }

    public static void closeDescriptor(AssetFileDescriptor assetDescriptor)
    {
        if (assetDescriptor != null)
        {
            try
            {
                assetDescriptor.close();
            }
            catch (Exception e)
            {
                Logger.error(e);
            }
        }
    }
}