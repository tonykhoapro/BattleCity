package com.mygdx.battlecity.CoreObject.GameObject;

import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

import java.util.Random;

public class Grenade extends Item {
    public Grenade(){
        animationItem = new SpriteComponent("Grenade");
        AddComponent(animationItem);
        AddComponent(hitBox);
        Random ran = new Random();

        SetPosition(ran.nextInt(500), ran.nextInt(500));
    }


}
