package com.mauriciotogneri.mandy.resources;

import jbox2d.dynamics.BodyType;

import java.util.List;

public class Model
{
    public final BodyType type;
    public final String path;
    public Structure structure;
    public List<Mesh> meshes;

    public Model(BodyType type, String path)
    {
        this.type = type;
        this.path = path;
    }

    public void load(Structure structure, List<Mesh> meshes)
    {
        this.structure = structure;
        this.meshes = meshes;
    }
}