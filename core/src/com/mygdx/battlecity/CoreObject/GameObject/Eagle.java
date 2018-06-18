package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class Eagle extends Actor {
    SpriteComponent spriteALive = new SpriteComponent("EagleNormal");
    SpriteComponent spriteDead = new SpriteComponent("EagleDead");
    HitBox hitBox = new HitBox(32, 32, BodyDef.BodyType.StaticBody);

    public Eagle(){
        AddComponent(spriteALive);
        AddComponent(hitBox);
    }

    @Override
    public void OnTick(float dt) {
        super.OnTick(dt);
    }

    @Override
    public void OnBeginHit(Actor other) {
        super.OnBeginHit(other);
        if (Bullet.class.isInstance(other)) {
            Activate(new Explosion(GetPosition().x, GetPosition().y));
            AddComponent(spriteDead);
            RemoveComponent(spriteALive);
            RemoveComponent(hitBox);
        }
    }

}
