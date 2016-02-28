package com.mauriciotogneri.mandy.loaders;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.mauriciotogneri.mandy.debug.Logger;
import com.mauriciotogneri.mandy.helpers.ResourceHelper;
import com.mauriciotogneri.mandy.resources.Music;

public class MusicLoader
{
    public Music load(String path, AssetManager assetManager)
    {
        MediaPlayer player = new MediaPlayer();
        AssetFileDescriptor assetDescriptor = null;

        try
        {
            assetDescriptor = assetManager.openFd(path);

            player.setDataSource(assetDescriptor.getFileDescriptor(), assetDescriptor.getStartOffset(), assetDescriptor.getLength());
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setVolume(0.2f, 0.2f);
            player.prepare();
            player.setLooping(true);
        }
        catch (Exception e)
        {
            Logger.error(e);
        }
        finally
        {
            ResourceHelper.closeDescriptor(assetDescriptor);
        }

        return new Music(player);
    }
}