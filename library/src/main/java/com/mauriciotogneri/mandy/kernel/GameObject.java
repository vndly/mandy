package com.mauriciotogneri.mandy.kernel;

import android.graphics.Color;

import com.mauriciotogneri.mandy.graphics.Renderer;
import com.mauriciotogneri.mandy.input.Input;
import com.mauriciotogneri.mandy.physics.Physics;
import com.mauriciotogneri.mandy.resources.Mesh;
import com.mauriciotogneri.mandy.resources.Model;
import com.mauriciotogneri.mandy.screen.Camera;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

public class GameObject implements Entity
{
    protected float z;

    protected final Body body;
    protected final Mesh[] meshes;
    protected final Mesh[] shadow;

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

    public GameObject(float x, float y, float z, BodyType type, Model model, Physics physics, int group, boolean isSensor)
    {
        this.z = z;
        this.body = model.structure.addTo(x, y, type, physics, group, isSensor);
        this.meshes = model.meshes;
        this.shadow = model.structure.getShadows(Color.argb(255, 255, 100, 180)); // pink
        this.displayMode = DisplayMode.NORMAL;

        this.body.setUserData(this);
    }

    public Body getBody()
    {
        return body;
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