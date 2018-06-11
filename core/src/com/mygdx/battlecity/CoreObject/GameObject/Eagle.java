package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class Eagle extends Actor {
    public Eagle(){
        AddComponent(new SpriteComponent("Eagle"));
        AddComponent(new HitBox(32, 32, BodyDef.BodyType.StaticBody));
    }
}
