package com.mauriciotogneri.mandy.input;

public class TouchEvent
{
    public boolean pressed = false;
    public int x;
    public int y;

    public boolean isPressed()
    {
        return pressed;
    }

    public boolean isPressed(float left, float right, float bottom, float top)
    {
        return (pressed) && (x >= left) && (x <= right) && (y >= bottom) && (y <= top);
    }

    public boolean isPressedHorizontal(float left, float right)
    {
        return (pressed) && (x >= left) && (x <= right);
    }

    public boolean isPressedVertical(float bottom, float top)
    {
        return (pressed) && (y >= bottom) && (y <= top);
    }
}