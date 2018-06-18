package com.mygdx.battlecity.CoreObject.GameObject;

import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class Tree extends MapObject {

    public Tree() {
        RemoveComponent(hitBox);
        SpriteComponent spriteComponent = new SpriteComponent("Tree", 10);
        AddComponent(spriteComponent);
    }
}
