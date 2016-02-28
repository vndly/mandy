package com.mauriciotogneri.mandy.loaders;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.mauriciotogneri.mandy.helpers.ResourceHelper;
import com.mauriciotogneri.mandy.json.JsonMesh;
import com.mauriciotogneri.mandy.json.JsonModel;
import com.mauriciotogneri.mandy.resources.Mesh;
import com.mauriciotogneri.mandy.resources.Model;
import com.mauriciotogneri.mandy.resources.Structure;

import java.util.ArrayList;
import java.util.List;

public class ModelLoader
{
    public Model getModel(String path, Context context)
    {
        AssetManager assetManager = context.getAssets();

        Gson gson = new Gson();

        String content = ResourceHelper.readFromAssetsAsString(assetManager, path);
        JsonModel jsonModel = gson.fromJson(content, JsonModel.class);

        List<Mesh> meshes = new ArrayList<>();

        StructureLoader structureLoader = new StructureLoader();
        Structure structure = structureLoader.load(jsonModel.body);

        MeshLoader meshLoader = new MeshLoader();

        for (JsonMesh jsonMesh : jsonModel.meshes)
        {
            Mesh mesh = meshLoader.load(jsonMesh);
            meshes.add(mesh);
        }

        return new Model(structure, meshes.toArray(new Mesh[meshes.size()]));
    }
}