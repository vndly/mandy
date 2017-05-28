package com.mauriciotogneri.mandy.audio;

import android.media.MediaPlayer;

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
                e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public void stop()
    {
        try
        {
            player.stop();
            player.seekTo(0);
            player.release();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}