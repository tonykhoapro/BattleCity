package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class BrickWallTopLeft extends BrickWall {
    public BrickWallTopLeft() {
        AddComponent(new SpriteComponent("BrickWallTopLeft"));
        AddComponent(new HitBox(8, 8, BodyDef.BodyType.StaticBody));
    }
}
