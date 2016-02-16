package com.mauriciotogneri.mandy.resources;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.mauriciotogneri.mandy.debug.Logger;
import com.mauriciotogneri.mandy.helpers.ResourceHelper;

public class Music
{
    private final String path;
    private MediaPlayer player;
    private int position = 0;

    public Music(String path)
    {
        this.path = path;
    }

    public void load(AssetManager assetManager)
    {
        AssetFileDescriptor assetDescriptor = null;

        try
        {
            assetDescriptor = assetManager.openFd(path);

            player = new MediaPlayer();
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
    }

    public void resume()
    {
        if ((player != null) && (!player.isPlaying()))
        {
            try
            {
                player.seekTo(position);
                player.start();
            }
            catch (Exception e)
            {
                Logger.error(e);
            }
        }
    }

    public void pause()
    {
        if (player != null)
        {
            try
            {
                player.pause();
                position = player.getCurrentPosition();
            }
            catch (Exception e)
            {
                Logger.error(e);
            }
        }
    }

    public void stop()
    {
        if (player != null)
        {
            try
            {
                player.stop();
                player.seekTo(0);
                player.release();
            }
            catch (Exception e)
            {
                Logger.error(e);
            }
        }
    }

    public boolean isPlaying()
    {
        return ((player != null) && player.isPlaying());
    }
}