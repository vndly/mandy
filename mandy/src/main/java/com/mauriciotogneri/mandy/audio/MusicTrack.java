package com.mauriciotogneri.mandy.audio;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.mauriciotogneri.mandy.loaders.ResourceLoader;

public class MusicTrack
{
    private final AssetFileDescriptor assetDescriptor;

    public MusicTrack(AssetFileDescriptor assetDescriptor)
    {
        this.assetDescriptor = assetDescriptor;
    }

    public Music create()
    {
        try
        {
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(assetDescriptor.getFileDescriptor(), assetDescriptor.getStartOffset(), assetDescriptor.getLength());
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setVolume(0.5f, 0.5f);
            player.prepare();
            player.setLooping(true);

            return new Music(player);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unable to load music: " + assetDescriptor.toString());
        }
    }

    public void close(ResourceLoader resourceLoader)
    {
        resourceLoader.closeDescriptor(assetDescriptor);
    }
}