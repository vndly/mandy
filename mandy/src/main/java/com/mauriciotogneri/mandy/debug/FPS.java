package com.mauriciotogneri.mandy.debug;

import android.graphics.Typeface;
import android.widget.TextView;

public class FPS
{
    private final TextView textView;
    private long framesStart = System.nanoTime();
    private int frames = 0;

    public FPS(TextView textView)
    {
        this.textView = textView;
        this.textView.setTypeface(Typeface.MONOSPACE);
    }

    public void tick()
    {
        frames++;

        if ((System.nanoTime() - framesStart) > 1E9)
        {
            textView.post(new Runnable()
            {
                final int totalFrames = frames;

                @Override
                public void run()
                {
                    String text = "FPS: " + totalFrames;
                    textView.setText(text);
                }
            });

            frames = 0;
            framesStart = System.nanoTime();
        }
    }
}