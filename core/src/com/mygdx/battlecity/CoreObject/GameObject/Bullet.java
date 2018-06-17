package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class Bullet extends Actor {

    public enum BState {
        fire, // đang bắn
        explosion, // nổ
    }

    private BState state = BState.fire;

    HitBox hitBox = new HitBox(8, 8, BodyDef.BodyType.DynamicBody, true);
    SpriteComponent spriteBullet = new SpriteComponent("Bullet");
    SpriteComponent AnimationExplosion = new SpriteComponent("Explosion", 0.1f, true);

    float statetime;
    {
        statetime = 0;
    }
    float angle;
    float speed = 200;
    Vector2 velocity = new Vector2(0, 0);

    public Bullet(float x, float y, int angle) {
        AddComponent(hitBox);
        AddComponent(spriteBullet);
        this.angle = angle;
        if (angle == 0) {
            velocity.y = speed;

        } else if (angle == 90) {
            velocity.x = -speed;

        } else if (angle == 270) {
            velocity.x = speed;

        } else if (angle == 180) {
            velocity.y = -speed;

        } else assert (false);

        SetPosition(x, y);
    }


    @Override
    public void OnActivate() {
        super.OnActivate();
        hitBox.SetVelocity(velocity.x, velocity.y);
        SetRotation(angle);
    }

    @Override
    public void OnTick(float dt) {
        super.OnTick(dt);
        if(state == BState.explosion)
            statetime+=dt;
       if (AnimationExplosion.getAnimation().isAnimationFinished(statetime))
       {
           Deactivate(this);
       }
    }

    @Override
    public void OnBeginHit(Actor other) {
        super.OnBeginHit(other);
        if (BrickWall.class.isInstance(other) || Eagle.class.isInstance(other) || Enemy.class.isInstance(other)) {
            state = BState.explosion;
            AddComponent(AnimationExplosion);
            RemoveComponent(hitBox);
            RemoveComponent(spriteBullet);
            if (!Enemy.class.isInstance(other))
                Deactivate(other);
        }
    }
}
