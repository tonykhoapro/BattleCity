package com.mygdx.battlecity.CoreObject.GameObject;

import com.mygdx.battlecity.CoreObject.SpriteComponent;

import java.util.Random;

public class Timer extends Item {
    public Timer() {
        animationItem = new SpriteComponent("Timer");
        AddComponent(animationItem);
        AddComponent(hitBox);
        Random ran = new Random();

        SetPosition(ran.nextInt(500), ran.nextInt(500));
    }
}
