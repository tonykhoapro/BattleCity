package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Game;

public class Eagle extends Actor {
    public Eagle() {
        AddComponent(new SpriteComponent("EagleNormal"));
        AddComponent(new HitBox(32 / Game.PPM, 32 / Game.PPM, BodyDef.BodyType.StaticBody));
    }
}
