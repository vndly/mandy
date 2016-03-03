package com.mauriciotogneri.mandy.resources;

public class Model
{
    private final Structure structure;
    private final Mesh[] meshes;

    public Model(Structure structure, Mesh[] meshes)
    {
        this.structure = structure;
        this.meshes = meshes;
    }

    public Structure structure()
    {
        return structure;
    }

    public Mesh[] meshes()
    {
        return meshes;
    }
}