package com.mauriciotogneri.mandy.resources;

import android.media.SoundPool;

public class Sound
{
    private final int id;
    private final SoundPool soundPool;

    public Sound(int id, SoundPool soundPool)
    {
        this.id = id;
        this.soundPool = soundPool;
    }

    public void play()
    {
        if (soundPool != null)
        {
            soundPool.play(id, 1, 1, 1, 0, 1);
        }
    }
}