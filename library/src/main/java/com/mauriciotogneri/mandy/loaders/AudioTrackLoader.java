package com.mauriciotogneri.mandy.loaders;

import android.content.res.AssetManager;

import com.mauriciotogneri.mandy.resources.AudioTrack;

import java.io.IOException;

public class AudioTrackLoader
{
    public AudioTrack load(String path, AssetManager assetManager) throws IOException
    {
        return new AudioTrack(assetManager.openFd(path));
    }
}