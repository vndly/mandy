package com.mauriciotogneri.mandy.loaders;

import android.content.Context;

import com.mauriciotogneri.mandy.audio.AudioManager;
import com.mauriciotogneri.mandy.resources.Music;
import com.mauriciotogneri.mandy.resources.Sound;

public class ResourceLoader
{
    private final Context context;

    public ResourceLoader(Context context)
    {
        this.context = context;
    }

    public void loadSounds(Sound[] sounds)
    {
        AudioManager audioManager = AudioManager.getInstance();
        audioManager.loadSounds(sounds, context);
    }

    public void loadAudios(Music[] musics)
    {
        AudioManager audioManager = AudioManager.getInstance();
        audioManager.loadMusic(musics, context);
    }
}