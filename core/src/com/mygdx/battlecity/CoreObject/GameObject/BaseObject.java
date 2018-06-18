package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.Component;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Tickable;

public abstract class BaseObject extends Actor {
    public enum Direction {
        Up,
        Right,
        Down,
        Left
    }
    Bullet bullet = null;
    HitBox hitBox = new HitBox(24, 24, BodyDef.BodyType.DynamicBody);

    SpriteComponent normalState;
    SpriteComponent appearingState = new SpriteComponent("Twinkle", 0.06f, true);
    SpriteComponent protectedState = new SpriteComponent("Shield", 0.1f, true);

    SpriteComponent currentState = appearingState;

    float speed = 120;



    Vector2 respawnPosition = new Vector2();
    float respawnRotation = 180;

    static final float APPEAR_TIME = 1.6f;
    float appearAccumTime = 0;

    static final float PROTECTED_TIME = 1;
    float protectedAccumTime = 0;


    public BaseObject(SpriteComponent spriteComponent) {
        normalState = spriteComponent;
        AddComponent(hitBox);
        AddComponent(currentState);
    }

    @Override
    public void OnTick(float dt) {
        super.OnTick(dt);

        if (currentState == normalState) {
            accum += dt;
            Update(dt);
        } else if (currentState == protectedState) {
            OnProtected(dt);
        } else if (currentState == appearingState) {
            OnApperaing(dt);
        }

        //if (!isAlive) Respawn();
    }

    void OnProtected(float dt) {
        protectedAccumTime += dt;
        if (protectedAccumTime >= PROTECTED_TIME) {
            protectedAccumTime = 0;
            RemoveComponent(currentState);
            currentState = normalState;
            AddComponent(currentState);
        }
    }

    void OnApperaing(float dt) {
        appearAccumTime += dt;
        if (appearAccumTime >= APPEAR_TIME) {
            appearAccumTime = 0;
            RemoveComponent(currentState);
            currentState = protectedState;
            AddComponent(currentState);
            AddComponent(normalState);
        }
    }

    abstract void Update(float dt);

    public void Respawn() {
        if (currentState == normalState) {
            Activate(new Explosion(GetPosition().x, GetPosition().y));

            RemoveComponent(currentState);
            currentState = appearingState;
            AddComponent(currentState);
            SetPosition(respawnPosition);
            SetRotation(respawnRotation);
            //hitBox.SetVelocity(0, 0);
        }
    }

    public void Shoot() {

        if (accum >= cooldown) {
            accum = 0;
            if (bullet == null) {
                bullet = new Bullet(GetPosition().x, GetPosition().y, (int) GetRotation(), this);
            }
            Activate(bullet);
        }


    }

    protected float cooldown = 0.15f;
    private float accum = 0;

    public void MoveDirection(Direction direction) {
        if (direction == Direction.Up) {
            hitBox.SetVelocity(0, speed);
            SetRotation(0);
        } else if (direction == Direction.Down) {
            hitBox.SetVelocity(0, -speed);
            SetRotation(180);
        } else if (direction == Direction.Left) {
            hitBox.SetVelocity(-speed, 0);
            SetRotation(90);
        } else if (direction == Direction.Right) {
            hitBox.SetVelocity(speed, 0);
            SetRotation(-90);
        }
    }


    @Override
    public void OnBeginHit(Actor other) {
        super.OnBeginHit(other);
        if (Bullet.class.isInstance(other)) {
            Bullet bullet = (Bullet) other;
            if (bullet.getOwner() != this) {
                Respawn();
            }
        }
    }
}
