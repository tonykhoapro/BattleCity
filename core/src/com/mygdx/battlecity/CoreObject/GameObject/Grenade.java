package com.mygdx.battlecity.CoreObject.GameObject;

import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class Grenade extends Item {
    public Grenade(){
        AnimationItem = new SpriteComponent("Grenade", 0.2f, true);
        AddComponent(AnimationItem);
        AddComponent(hitBox);
        SetPosition(100, 300);
    }
}
