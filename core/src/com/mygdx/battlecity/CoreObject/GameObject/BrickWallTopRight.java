package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class BrickWallTopRight extends BrickWall {
    public BrickWallTopRight(){
        AddComponent(new SpriteComponent("BrickWallTopRight"));
        AddComponent(new HitBox(8, 8, BodyDef.BodyType.StaticBody));
    }
}
