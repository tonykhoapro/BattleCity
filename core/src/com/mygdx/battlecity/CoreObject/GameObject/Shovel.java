package com.mygdx.battlecity.CoreObject.GameObject;

import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

import java.util.Random;

public class Shovel extends Item{
    public Shovel(){
        animationItem = new SpriteComponent("Shovel");
        AddComponent(animationItem);
        AddComponent(hitBox);
        Random ran = new Random();

        SetPosition(ran.nextInt(500), ran.nextInt(500));
    }


    
}
