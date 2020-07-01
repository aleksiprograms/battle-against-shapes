package com.aleksiprograms.battleagainstshapes.managers;

import com.aleksiprograms.battleagainstshapes.resources.SaveData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

/**
 * Manages the loading and saving of the game data.
 */
public class SaveManager {

    private static FileHandle file = Gdx.files.local("save_data.json");
    private SaveData saveData;

    public SaveManager() {
        try {
            Json json = new Json();
            saveData = json.fromJson(
                    SaveData.class,
                    Base64Coder.decodeString(file.readString()));
        } catch (Exception ex) {
            saveData = new SaveData();
        }
    }

    public void save() {
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        file.writeString(Base64Coder.encodeString(
                json.prettyPrint(getSaveData())),
                false);
    }

    public SaveData getSaveData() {
        return saveData;
    }
}