package com.mauriciotogneri.mandy.resources;

import android.media.MediaPlayer;

import com.mauriciotogneri.mandy.debug.Logger;

public class Music
{
    private final MediaPlayer player;
    private int position = 0;

    public Music(MediaPlayer player)
    {
        this.player = player;
    }

    public void resume()
    {
        if (!player.isPlaying())
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

    public void stop()
    {
        try
        {
            player.stop();
            player.seekTo(0);
        }
        catch (Exception e)
        {
            Logger.error(e);
        }
    }

    public void destroy()
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