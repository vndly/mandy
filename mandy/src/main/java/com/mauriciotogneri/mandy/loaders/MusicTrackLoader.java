package com.mauriciotogneri.mandy.loaders;

import android.content.res.AssetManager;

import com.mauriciotogneri.mandy.audio.MusicTrack;

import java.io.IOException;

public class MusicTrackLoader
{
    public MusicTrack load(String path, AssetManager assetManager) throws IOException
    {
        return new MusicTrack(assetManager.openFd(path));
    }
}