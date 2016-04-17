package com.mauriciotogneri.mandy.loaders;

import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.mauriciotogneri.mandy.helpers.ResourceLoader;
import com.mauriciotogneri.mandy.json.JsonModel;
import com.mauriciotogneri.mandy.json.JsonTriangle;
import com.mauriciotogneri.mandy.resources.Mesh;
import com.mauriciotogneri.mandy.resources.Model;
import com.mauriciotogneri.mandy.resources.Structure;

public class ModelLoader
{
    private final ResourceLoader resourceLoader;
    private final Gson gson = new Gson();

    public ModelLoader()
    {
        this.resourceLoader = new ResourceLoader();
    }

    public Model load(String path, AssetManager assetManager)
    {
        String content = resourceLoader.readFromAssetsAsString(assetManager, path);
        JsonModel jsonModel = gson.fromJson(content, JsonModel.class);

        StructureLoader structureLoader = new StructureLoader();
        Structure structure = structureLoader.load(jsonModel.body, jsonModel.meshes);

        Mesh[] meshes = meshes(jsonModel.meshes);

        return new Model(structure, meshes);
    }

    private Mesh[] meshes(JsonTriangle[][] list)
    {
        Mesh[] meshes = new Mesh[list.length];
        MeshLoader meshLoader = new MeshLoader();

        for (int i = 0; i < list.length; i++)
        {
            JsonTriangle[] jsonMesh = list[i];

            Mesh mesh = meshLoader.load(jsonMesh);

            meshes[i] = mesh;
        }

        return meshes;
    }
}