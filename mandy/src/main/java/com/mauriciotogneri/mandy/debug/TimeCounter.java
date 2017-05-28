package com.mauriciotogneri.mandy.debug;

import android.graphics.Typeface;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class TimeCounter
{
    private long time = 0;
    private int times = 0;
    private long start = 0;
    private final String name;
    private final boolean enabled;
    private final TimeUnit timeUnit;
    private final TextView textView;
    private final String abbreviation;

    public TimeCounter(TextView textView, String name, TimeUnit timeUnit, boolean enabled)
    {
        this.textView = textView;
        this.textView.setTypeface(Typeface.MONOSPACE);

        this.name = name;
        this.timeUnit = timeUnit;
        this.enabled = enabled;
        this.abbreviation = abbreviation(timeUnit);
    }

    private String abbreviation(TimeUnit timeUnit)
    {
        switch (timeUnit)
        {
            case SECONDS:
                return "s";

            case MICROSECONDS:
                return "us";

            case MILLISECONDS:
                return "ms";

            case NANOSECONDS:
                return "ns";

            default:
                return timeUnit.name();
        }
    }

    public void start()
    {
        start = System.nanoTime();
    }

    public void stop()
    {
        if (enabled)
        {
            time += System.nanoTime() - start;
            times++;

            textView.post(new Runnable()
            {
                final String value = String.format("%s %s", timeUnit.convert(time / times, TimeUnit.NANOSECONDS), abbreviation);

                @Override
                public void run()
                {
                    String text = name + value;
                    textView.setText(text);
                }
            });
        }
    }
}