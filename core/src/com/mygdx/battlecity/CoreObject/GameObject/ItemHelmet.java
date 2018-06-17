package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class ItemHelmet extends Item {
    public ItemHelmet(){
        AnimationItem = new SpriteComponent("Helmet", 0.2f, true);
        AddComponent(AnimationItem);
        AddComponent(hitBox);
        SetPosition(50, 50);
    }
}

