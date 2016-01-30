package com.mauriciotogneri.mandy.kernel;

import com.mauriciotogneri.mandy.graphics.Renderer;
import com.mauriciotogneri.mandy.input.Input;
import com.mauriciotogneri.mandy.screen.Camera;

public interface Entity
{
    // A GameObject can be:
    // updatable -> update(delta)
    // localizable -> (x, y, z, angle, scale)
    // renderable -> OpenGL
    // physicsable -> Box2D
    // dynamic / static
    // collisionable

    void render(Renderer renderer);

    void update(float delta, Input input, Camera camera);
}