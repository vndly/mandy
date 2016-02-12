package com.mauriciotogneri.mandy.loaders;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.mauriciotogneri.mandy.json.JsonMesh;
import com.mauriciotogneri.mandy.json.JsonModel;
import com.mauriciotogneri.mandy.resources.Mesh;
import com.mauriciotogneri.mandy.resources.Model;
import com.mauriciotogneri.mandy.resources.Structure;
import com.mauriciotogneri.mandy.util.ResourceHelper;

import java.util.ArrayList;
import java.util.List;

public class ModelLoader
{
    public void load(Model[] models, Context context)
    {
        AssetManager assetManager = context.getAssets();

        Gson gson = new Gson();

        for (Model model : models)
        {
            String content = ResourceHelper.readFromAssetsAsString(assetManager, model.path);
            JsonModel jsonModel = gson.fromJson(content, JsonModel.class);
            loadModel(model, jsonModel);
        }
    }

    private void loadModel(Model model, JsonModel json)
    {
        List<Mesh> meshes = new ArrayList<>();

        StructureLoader structureLoader = new StructureLoader();
        Structure structure = structureLoader.load(json.body);

        MeshLoader meshLoader = new MeshLoader();

        for (JsonMesh jsonMesh : json.meshes)
        {
            Mesh mesh = meshLoader.load(jsonMesh);
            meshes.add(mesh);
        }

        model.load(structure, meshes.toArray(new Mesh[meshes.size()]));
    }
}