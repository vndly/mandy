package com.mauriciotogneri.mandy.resources;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;

import com.mauriciotogneri.mandy.debug.Logger;
import com.mauriciotogneri.mandy.util.ResourceHelper;

public class Sound
{
    private final String path;
    private int id = 0;
    private SoundPool soundPool;

    public Sound(String path)
    {
        this.path = path;
    }

    public void load(AssetManager assetManager, SoundPool pool)
    {
        AssetFileDescriptor assetDescriptor = null;

        try
        {
            assetDescriptor = assetManager.openFd(path);
            id = pool.load(assetDescriptor, 1);
            soundPool = pool;
        }
        catch (Exception e)
        {
            Logger.error(e);
        }
        finally
        {
            ResourceHelper.closeDescriptor(assetDescriptor);
        }
    }

    public void play()
    {
        if (soundPool != null)
        {
            soundPool.play(id, 1, 1, 1, 0, 1);
        }
    }
}