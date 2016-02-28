package com.mauriciotogneri.mandy.audio;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.SoundPool;

import com.mauriciotogneri.mandy.resources.Sound;

public class AudioManager
{
    private final SoundPool soundPool;

    private static AudioManager instance;

    public static synchronized AudioManager getInstance()
    {
        if (instance == null)
        {
            instance = new AudioManager();
        }

        return instance;
    }

    @SuppressWarnings("deprecation")
    private AudioManager()
    {
        this.soundPool = new SoundPool(20, android.media.AudioManager.STREAM_MUSIC, 0);
    }

    public void loadSounds(Sound[] sounds, Context context)
    {
        AssetManager assetManager = context.getAssets();

        for (Sound sound : sounds)
        {
            sound.load(assetManager, soundPool);
        }
    }

    public void resume()
    {
        soundPool.autoResume();
    }

    public void pause()
    {
        soundPool.autoPause();
    }

    public void stop()
    {
        soundPool.autoPause();

        //        Collection<Integer> soundsIds = soundsMap.values();
        //
        //        for (Integer soundId : soundsIds)
        //        {
        //            soundPool.unload(soundId);
        //        }
        //
        //        soundPool.release();
    }
}