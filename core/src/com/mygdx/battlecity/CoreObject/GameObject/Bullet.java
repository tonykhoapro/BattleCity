package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Game;

public class Bullet extends Actor {
    HitBox hitBox = new HitBox(16 / Game.PPM, 16 / Game.PPM, BodyDef.BodyType.DynamicBody, true, true);
    float speed = 500 / Game.PPM;
    Vector2 velocity = new Vector2(0, 0);

    public Tank getOwner() {
        return owner;
    }

    Tank owner;
    float angle = 0;

    public Bullet(float x, float y, int angle, Tank owner) {
        Init(x, y, angle, owner, speed);
    }

    public Bullet(float x, float y, int angle, Tank owner, float speed) {
        Init(x, y, angle, owner, speed);
    }

    private void Init(float x, float y, int angle, Tank owner, float speed) {
        this.speed = speed;
        this.owner = owner;
        AddComponent(hitBox);
        AddComponent(new SpriteComponent("Bullet"));
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
    public void OnBeginHit(Actor other) {
        super.OnBeginHit(other);
        if (other == owner || Bullet.class.isInstance(other)) return;

        if (BrickWall.class.isInstance(other)) {
            Deactivate(other);
        }
        if(!Water.class.isInstance(other)) Deactivate(this);
    }

    @Override
    public void OnDeactivate() {
        super.OnDeactivate();
        Activate(new Explosion(GetPosition().x, GetPosition().y));
    }
}
