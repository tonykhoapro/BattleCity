package com.mygdx.battlecity.CoreObject.GameObject;

import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class Steel extends MapObject {
    public Steel(float x, float y) {
        SetPosition(x, y);
        AddComponent(new SpriteComponent("SteelWall"));
    }
}
