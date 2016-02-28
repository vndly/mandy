package com.mauriciotogneri.mandy.input;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.mauriciotogneri.mandy.screen.Camera;

public class Input implements OnTouchListener
{
    private final Object touchLock = new Object();
    private final TouchEvent[] touchEvents = new TouchEvent[TOUCH_EVENT_SIZE];
    private final Camera camera;

    private static final int TOUCH_EVENT_SIZE = 5;

    public Input(View view, Camera camera)
    {
        this.camera = camera;

        for (int i = 0; i < TOUCH_EVENT_SIZE; i++)
        {
            this.touchEvents[i] = new TouchEvent();
        }

        view.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event)
    {
        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);

        if (pointerId < TOUCH_EVENT_SIZE)
        {
            switch (event.getActionMasked())
            {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                case MotionEvent.ACTION_MOVE:
                    updateTouchEvent(pointerId, event.getX(pointerIndex), event.getY(pointerIndex));
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL:
                    updateTouchEvent(pointerId);
                    break;
            }
        }

        return true;
    }

    private void updateTouchEvent(int index, float x, float y)
    {
        synchronized (touchLock)
        {
            TouchEvent touchEvent = touchEvents[index];
            touchEvent.pressed = true;
            touchEvent.x = (int) ((x * camera.width) / camera.screenWidth);
            touchEvent.y = (int) (((camera.screenHeight - y) * camera.height) / camera.screenHeight);
        }
    }

    private void updateTouchEvent(int index)
    {
        synchronized (touchLock)
        {
            TouchEvent touchEvent = touchEvents[index];
            touchEvent.pressed = false;
        }
    }

    public boolean isPressed()
    {
        synchronized (touchLock)
        {
            for (int i = 0; i < TOUCH_EVENT_SIZE; i++)
            {
                if (touchEvents[i].isPressed())
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isPressed(float left, float right, float bottom, float top)
    {
        synchronized (touchLock)
        {
            for (int i = 0; i < TOUCH_EVENT_SIZE; i++)
            {
                if (touchEvents[i].isPressed(left, right, bottom, top))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isPressedHorizontal(float left, float right)
    {
        synchronized (touchLock)
        {
            for (int i = 0; i < TOUCH_EVENT_SIZE; i++)
            {
                if (touchEvents[i].isPressedHorizontal(left, right))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isPressedRightHalf()
    {
        return isPressedHorizontal(camera.width / 2, camera.width);
    }

    public boolean isPressedLeftHalf()
    {
        return isPressedHorizontal(0, camera.width / 2);
    }

    public boolean isPressedVertical(float bottom, float top)
    {
        synchronized (touchLock)
        {
            for (int i = 0; i < TOUCH_EVENT_SIZE; i++)
            {
                if (touchEvents[i].isPressedVertical(bottom, top))
                {
                    return true;
                }
            }
        }

        return false;
    }
}