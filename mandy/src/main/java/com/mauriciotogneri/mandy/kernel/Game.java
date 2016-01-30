package com.mauriciotogneri.mandy.kernel;

import com.mauriciotogneri.mandy.graphics.Renderer;
import com.mauriciotogneri.mandy.input.Input;
import com.mauriciotogneri.mandy.screen.Camera;
import com.mauriciotogneri.mandy.screen.ScreenSize;

public interface Game
{
    void onUpdate(float delta, Input input, Camera camera);

    void onRender(Renderer renderer, Camera camera);

    ScreenSize getScreenSize();
}