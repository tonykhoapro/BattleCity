package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class BrickWall extends Actor {
    public BrickWall() {

    }

    @Override
    public void OnBeginHit(Actor other) {
        super.OnBeginHit(other);
        if (BrickWall.class.isInstance(other)) {
            Deactivate(this);
        }
    }
}
