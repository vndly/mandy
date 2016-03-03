package com.mauriciotogneri.mandy.screen;

public class ScreenSize
{
    private final int width;
    private final int height;

    private ScreenSize(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public int width(float ratio)
    {
        return (width != 0) ? width : (int) (height / ratio);
    }

    public int height(float ratio)
    {
        return (height != 0) ? height : (int) (width / ratio);
    }

    public static ScreenSize fromWidth(int width)
    {
        return new ScreenSize(width, 0);
    }

    public static ScreenSize fromHeight(int height)
    {
        return new ScreenSize(0, height);
    }
}