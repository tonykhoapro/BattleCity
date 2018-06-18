package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class BrickWallBottomLeft extends BrickWall {
    public BrickWallBottomLeft(){
        AddComponent(new SpriteComponent("BrickWallBottomLeft"));
    }
}