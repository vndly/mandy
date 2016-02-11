package com.mauriciotogneri.mandy.resources;

public class Model
{
    public final String path;
    public Structure structure;
    public Mesh[] meshes;

    public Model(String path)
    {
        this.path = path;
    }

    public void load(Structure structure, Mesh[] meshes)
    {
        this.structure = structure;
        this.meshes = meshes;
    }
}