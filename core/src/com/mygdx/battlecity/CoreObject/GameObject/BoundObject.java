package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class BoundObject extends Actor {
    public BoundObject(float x, float y){
        SetPosition(x, y);
        AddComponent(new HitBox(16, 16, BodyDef.BodyType.StaticBody));
        AddComponent( new SpriteComponent("Water"));
    }
}
