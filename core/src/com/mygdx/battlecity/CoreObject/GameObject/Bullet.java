package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class Bullet extends Actor {
    HitBox hitBox = new HitBox(8, 8, BodyDef.BodyType.DynamicBody, true);
    float speed = 240;
    Vector2 velocity = new Vector2(0, 0);

    public Bullet(float x, float y, int angle) {
        AddComponent(hitBox);
        AddComponent(new SpriteComponent("Bullet"));

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
        SetRotation(angle);
    }


    @Override
    public void OnActivate() {
        super.OnActivate();

        hitBox.SetVelocity(velocity.x, velocity.y);

    }
    
    @Override
    public void OnBeginHit(Actor other) {
        super.OnBeginHit(other);
        if (!BaseObject.class.isInstance(other) && !Bullet.class.isInstance(other)) {
            Deactivate(this);
            Deactivate(other);
        }
    }

    @Override
    public void OnDeactivate() {
        super.OnDeactivate();
        Activate(new Explosion(GetPosition().x, GetPosition().y));
    }
}
