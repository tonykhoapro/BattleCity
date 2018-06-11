package com.mygdx.battlecity.System;

import com.mygdx.battlecity.CoreObject.Scene;
import com.mygdx.battlecity.TickManager;


public class SceneManager extends System {

    public SceneManager(Scene scene) {
        main = scene;
    }

    public static void Load(Scene scene) {
        SceneManager sceneManager = (SceneManager) TickManager.getInstance().FindByType(SceneManager.class);
        assert sceneManager != null;
        if (scene != sceneManager.main) sceneManager.next = scene;
    }

    @Override
    public void OnActivate() {
        super.OnActivate();
        main.Begin();
    }

    @Override
    public void OnTick(float dt) {
        super.OnTick(dt);
        if (next != null) {
            main.End();
            main = next;
            main.Begin();
            next = null;
        }
    }

    private Scene main;
    private Scene next = null;
}
