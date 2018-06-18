package com.mygdx.battlecity.CoreObject.GameObject;

import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class Explosion extends Actor {
    public static final float EXPLOSION_TIME = 0.2f;
    float explosionAccumTime = 0;

    public Explosion(float x, float y) {
        SetPosition(x, y);
        AddComponent(new SpriteComponent("Explosion", 0.04f, true));
    }

    @Override
    public void OnTick(float dt) {
        super.OnTick(dt);
        explosionAccumTime += dt;
        if (explosionAccumTime >= EXPLOSION_TIME) {
            Deactivate(this);
        }
    }
}
