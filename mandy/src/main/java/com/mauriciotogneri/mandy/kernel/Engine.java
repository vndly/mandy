package com.mauriciotogneri.mandy.kernel;

import android.view.View;

import com.mauriciotogneri.mandy.debug.Logger;
import com.mauriciotogneri.mandy.graphics.Renderer;
import com.mauriciotogneri.mandy.input.Input;
import com.mauriciotogneri.mandy.screen.Camera;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Engine implements android.opengl.GLSurfaceView.Renderer
{
    private final Renderer renderer;

    private final Game game;

    private final View view;
    private Camera camera;
    private Input input;

    private long startTime;

    private Status status = null;
    private final Object statusLock = new Object();

    private enum Status
    {
        RUNNING, IDLE, PAUSED, FINISHED
    }

    public Engine(Game game, View view, String vertexShaderSource, String fragmentShaderSource)
    {
        this.game = game;
        this.view = view;
        this.renderer = new Renderer(vertexShaderSource, fragmentShaderSource);
    }

    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config)
    {
        renderer.onSurfaceCreated();
    }

    @Override
    public void onSurfaceChanged(GL10 glUnused, int width, int height)
    {
        if (camera == null)
        {
            camera = new Camera(game.screenSize(), width, height);
        }

        if (input == null)
        {
            input = new Input(view, camera);
        }

        renderer.onSurfaceChanged(width, height);

        synchronized (statusLock)
        {
            status = Status.RUNNING;
        }

        startTime = System.nanoTime();
    }

    @Override
    public void onDrawFrame(GL10 glUnused)
    {
        Status status;

        synchronized (statusLock)
        {
            status = this.status;
        }

        if (status == Status.RUNNING)
        {
            long currentTime = System.nanoTime();
            float delta = (currentTime - startTime) / 1E9f;
            startTime = currentTime;

            update(delta);
        }
        else if ((status == Status.PAUSED) || (status == Status.FINISHED))
        {
            synchronized (statusLock)
            {
                this.status = Status.IDLE;
                statusLock.notifyAll();
            }
        }
    }

    private void update(float delta)
    {
        game.onUpdate(delta, input, camera);

        renderer.start(camera);
        game.onRender(renderer, camera);
        renderer.stop();
    }

    public void pause(boolean finishing)
    {
        synchronized (statusLock)
        {
            if (status == Status.RUNNING)
            {
                if (finishing)
                {
                    status = Status.FINISHED;
                }
                else
                {
                    status = Status.PAUSED;

                    while (true)
                    {
                        try
                        {
                            statusLock.wait();
                            break;
                        }
                        catch (Exception e)
                        {
                            Logger.error(e);
                        }
                    }
                }
            }
        }
    }
}