package com.mauriciotogneri.mandy.kernel;

import android.graphics.Color;

import com.mauriciotogneri.mandy.graphics.Renderer;
import com.mauriciotogneri.mandy.input.Input;
import com.mauriciotogneri.mandy.physics.Physics;
import com.mauriciotogneri.mandy.resources.Mesh;
import com.mauriciotogneri.mandy.resources.Model;
import com.mauriciotogneri.mandy.screen.Camera;

import org.jbox2d.dynamics.Body;

import java.util.List;

public class GameObject implements Entity
{
    protected float z;

    protected final Body body;
    protected final List<Mesh> meshes;
    protected final List<Mesh> shadow;

    private DisplayMode displayMode;

    public enum DisplayMode
    {
        NORMAL, //
        SHADOW, //
        BOTH;

        public boolean displayMeshes()
        {
            return (this == NORMAL) || (this == BOTH);
        }

        public boolean displayShadow()
        {
            return (this == SHADOW) || (this == BOTH);
        }
    }

    public GameObject(float x, float y, float z, Model model, Physics physics)
    {
        this.z = z;
        this.body = model.structure.addTo(x, y, physics);
        this.meshes = model.meshes;
        this.shadow = model.structure.getShadows(Color.YELLOW);
        this.displayMode = DisplayMode.NORMAL;
    }

    @Override
    public void update(float delta, Input input, Camera camera)
    {
    }

    public void setDisplayMode(DisplayMode displayMode)
    {
        this.displayMode = displayMode;
    }

    @Override
    public void render(Renderer renderer)
    {
        if (displayMode.displayMeshes())
        {
            renderer.render(meshes, body.getPosition(), z, 1, 1, body.getAngle());
        }

        if (displayMode.displayShadow())
        {
            renderer.render(shadow, body.getPosition(), z, 1, 1, body.getAngle());
        }
    }
}