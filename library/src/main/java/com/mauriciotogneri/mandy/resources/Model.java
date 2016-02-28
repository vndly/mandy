package com.mauriciotogneri.mandy.resources;

public class Model
{
    public final Structure structure;
    public final Mesh[] meshes;

    public Model(Structure structure, Mesh[] meshes)
    {
        this.structure = structure;
        this.meshes = meshes;
    }
}