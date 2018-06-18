package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

import java.util.Random;

public class Item extends Actor {
    float statetime = 0;
    public SpriteComponent animationItem;
    public HitBox hitBox = new HitBox(30, 30, BodyDef.BodyType.StaticBody);

    float timeCounter;
    float spawnTime;
    Random rand = new Random();
    public Item() {
        spawnTime = rand.nextFloat() + 300;

    }

    @Override
    public void OnTick(float dt) {
        super.OnTick(dt);
        //flicker(dt);
//        timeCounter += dt;
//        if(timeCounter >= spawnTime) {
//            timeCounter = 0;
//            RemoveComponent(animationItem);
//            RemoveComponent(hitBox);
//        }
    }

    @Override
    public void OnBeginHit(Actor other) {
        super.OnBeginHit(other);
        if (Player.class.isInstance(other)) {
            Deactivate(this);
        }
    }

    public void flicker(float dt)
    {
        statetime += dt;
        int pause = (int)(statetime / 0.2) % 2;
        if (pause == 0)
        {
            AddComponent(animationItem);
        }
        else if (pause == 1)
        {
            RemoveComponent(animationItem);
        }
    }
}
