package com.mauriciotogneri.mandy.kernel;

import com.mauriciotogneri.mandy.graphics.Renderer;
import com.mauriciotogneri.mandy.input.Input;
import com.mauriciotogneri.mandy.screen.Camera;

public interface Entity
{
    void render(Renderer renderer);

    void update(float delta, Input input, Camera camera);
}