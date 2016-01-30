package com.mauriciotogneri.mandy.screen;

public class Camera
{
    public float x = 0;
    public float y = 0;

    public final int width;
    public final int height;

    public final int screenWidth;
    public final int screenHeight;

    public Camera(ScreenSize screenSize, int screenWidth, int screenHeight)
    {
        float ratioHeightWidth = (float) screenHeight / (float) screenWidth;
        float ratioWidthHeight = (float) screenWidth / (float) screenHeight;

        this.width = screenSize.getWidth(ratioHeightWidth);
        this.height = screenSize.getHeight(ratioWidthHeight);

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }
}