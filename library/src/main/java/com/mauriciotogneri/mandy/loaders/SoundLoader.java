package com.mauriciotogneri.mandy.loaders;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;

import com.mauriciotogneri.mandy.debug.Logger;
import com.mauriciotogneri.mandy.helpers.ResourceLoader;
import com.mauriciotogneri.mandy.resources.Sound;

public class SoundLoader
{
    private final ResourceLoader resourceLoader;
    private final SoundPool soundPool;

    @SuppressWarnings("deprecation")
    public SoundLoader()
    {
        this.resourceLoader = new ResourceLoader();
        this.soundPool = new SoundPool(20, android.media.AudioManager.STREAM_MUSIC, 0);
    }

    public Sound load(String path, AssetManager assetManager)
    {
        AssetFileDescriptor assetDescriptor = null;

        try
        {
            assetDescriptor = assetManager.openFd(path);
            int id = soundPool.load(assetDescriptor, 1);

            return new Sound(id, soundPool);
        }
        catch (Exception e)
        {
            Logger.error(e);

            throw new RuntimeException("Unable to load sound: " + path);
        }
        finally
        {
            resourceLoader.closeDescriptor(assetDescriptor);
        }
    }
}