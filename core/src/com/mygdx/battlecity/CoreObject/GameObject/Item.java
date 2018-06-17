package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class Item extends Actor {
    float statetime = 0;
    public SpriteComponent AnimationItem;
    public HitBox hitBox = new HitBox(30, 30,BodyDef.BodyType.StaticBody);

    public Item() {

    }

    @Override
    public void OnTick(float dt) {
        super.OnTick(dt);

        statetime+=dt;
        int Pause = (int)(statetime / 0.2) % 2;
        if (Pause == 0)
        {
            AddComponent(AnimationItem);
        }
        else if (Pause == 1)
        {
            RemoveComponent(AnimationItem);
        }
    }

    @Override
    public void OnBeginHit(Actor other) {
        super.OnBeginHit(other);
        if (Player.class.isInstance(other)) {
            Deactivate(this);
        }
    }
}
