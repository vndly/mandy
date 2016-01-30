package com.mauriciotogneri.mandy.kernel;

import com.mauriciotogneri.mandy.graphics.Renderer;
import com.mauriciotogneri.mandy.input.Input;
import com.mauriciotogneri.mandy.resources.Mesh;
import com.mauriciotogneri.mandy.resources.Model;
import com.mauriciotogneri.mandy.screen.Camera;

import java.util.List;

public class DisplayObject implements Entity
{
    private final float startX;
    private final float startY;
    private final float startZ;

    protected float x;
    protected float y;
    protected float z;

    protected final List<Mesh> meshes;

    public DisplayObject(float x, float y, float z, Model model)
    {
        this.x = x;
        this.y = y;
        this.z = z;

        this.startX = x;
        this.startY = y;
        this.startZ = z;

        this.meshes = model.meshes;
    }

    public void reset()
    {
        this.x = startX;
        this.y = startY;
        this.z = startZ;
    }

    @Override
    public void update(float delta, Input input, Camera camera)
    {
    }

    @Override
    public void render(Renderer renderer)
    {
        renderer.render(meshes, x, y, z, 1, 1, 0);
    }
}