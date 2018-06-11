package com.mygdx.battlecity.CoreObject.GameObject;

import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class Grenade extends Actor {
    public Grenade(){
        AddComponent(new SpriteComponent("Grenade"));
        SetPosition(100, 300);
    }


}
