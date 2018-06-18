package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Game;

public class Bound extends Actor {
    public Bound(float x, float y) {
        SetPosition(x, y);
        AddComponent(new HitBox(16 / Game.PPM, 16 / Game.PPM, BodyDef.BodyType.StaticBody));
        AddComponent(new SpriteComponent("Bound"));
    }
}
