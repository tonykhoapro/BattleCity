package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.Game;

public class MapObject extends Actor {
    HitBox hitBox = new HitBox(16 / Game.PPM, 16 / Game.PPM, BodyDef.BodyType.StaticBody);
    public MapObject() {
        AddComponent(hitBox);
    }
}
