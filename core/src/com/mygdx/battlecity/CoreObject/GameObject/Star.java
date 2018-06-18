package com.mygdx.battlecity.CoreObject.GameObject;

import com.mygdx.battlecity.CoreObject.SpriteComponent;

import java.util.Random;

public class Star extends Item{
    public Star() {
        animationItem = new SpriteComponent("Star");
        AddComponent(animationItem);
        AddComponent(hitBox);
        Random ran = new Random();

        SetPosition(ran.nextInt(500), ran.nextInt(500));
    }
}
