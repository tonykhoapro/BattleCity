package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class Enemy extends Actor {
    HitBox hitBox = new HitBox(32, 32, BodyDef.BodyType.StaticBody);

    public Enemy() {
        AddComponent(new SpriteComponent("Player_4"));
        AddComponent(hitBox);
        SetPosition(100, 200);
    }

    /*@Override
    public void OnPreHit(Actor other) {
        Tickable.Deactivate(other);
    }*/
}
