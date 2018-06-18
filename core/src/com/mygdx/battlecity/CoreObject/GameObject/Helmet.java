package com.mygdx.battlecity.CoreObject.GameObject;

import com.mygdx.battlecity.CoreObject.SpriteComponent;

import java.util.Random;

public class Helmet extends Item {
    public Helmet() {
        animationItem = new SpriteComponent("Helmet", 0.2f, true);
        AddComponent(animationItem);
        AddComponent(hitBox);
        Random ran = new Random();

        SetPosition(ran.nextInt(500), ran.nextInt(500));
    }
}

