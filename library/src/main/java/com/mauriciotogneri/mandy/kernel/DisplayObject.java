package com.mauriciotogneri.mandy.kernel;

import com.mauriciotogneri.mandy.graphics.Renderer;
import com.mauriciotogneri.mandy.input.Input;
import com.mauriciotogneri.mandy.resources.Mesh;
import com.mauriciotogneri.mandy.resources.Model;
import com.mauriciotogneri.mandy.screen.Camera;

public class DisplayObject implements Entity
{
    private final float startX;
    private final float startY;
    private final float startZ;

    private final float startScaleX;
    private final float startScaleY;

    protected float x;
    protected float y;
    protected float z;

    protected float scaleX;
    protected float scaleY;

    protected final Mesh[] meshes;

    public DisplayObject(float x, float y, float z, Model model)
    {
        this.x = x;
        this.y = y;
        this.z = z;

        this.scaleX = 1;
        this.scaleY = 1;

        this.startX = x;
        this.startY = y;
        this.startZ = z;

        this.startScaleX = scaleX;
        this.startScaleY = scaleY;

        this.meshes = model.meshes;
    }

    public void reset()
    {
        this.x = startX;
        this.y = startY;
        this.z = startZ;

        this.scaleX = startScaleX;
        this.scaleY = startScaleY;
    }

    @Override
    public void update(float delta, Input input, Camera camera)
    {
    }

    @Override
    public void render(Renderer renderer)
    {
        renderer.render(meshes, x, y, z, scaleX, scaleY, 0);
    }
}