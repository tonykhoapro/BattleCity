package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.Component;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Game;
import com.mygdx.battlecity.Tickable;

public abstract class Tank extends Actor {
    public enum Direction {
        Up,
        Right,
        Down,
        Left
    }

    HitBox hitBox = new HitBox(24 / Game.PPM, 24 / Game.PPM, BodyDef.BodyType.DynamicBody);

    SpriteComponent normalState;
    SpriteComponent appearingState = new SpriteComponent("Twinkle", 0.06f, true);
    SpriteComponent protectedState = new SpriteComponent("Shield", 0.1f, true);

    SpriteComponent currentState = appearingState;

    float speed = 140 / Game.PPM;
    float speedbullet = 400 / Game.PPM;

    Vector2 respawnPosition = new Vector2(0, 0);
    float respawnRotation = 0;

    static final float APPEAR_TIME = 1.6f;
    float appearAccumTime = 0;

    static final float PROTECTED_TIME = 1;
    float protectedAccumTime = 0;


    public Tank(SpriteComponent spriteComponent) {
        normalState = spriteComponent;
        //AddComponent(hitBox);
        AddComponent(currentState);
    }

    @Override
    public void OnActivate() {
        super.OnActivate();
        Respawn();
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

            AddComponent(hitBox);
        }
    }

    abstract void Update(float dt);

    public void Respawn() {
        if (currentState == normalState) {
            Activate(new Explosion(GetPosition().x, GetPosition().y));

            RemoveComponent(currentState);
            currentState = appearingState;
            AddComponent(currentState);
            SetPosition(respawnPosition.x, respawnPosition.y);
            SetRotation(respawnRotation);
            RemoveComponent(hitBox);
        }
    }

    public void Shoot() {
        if (accum >= cooldown) {
            accum = 0;
            Activate(new Bullet(GetPosition().x, GetPosition().y, (int) GetRotation(), this, speedbullet));
        }
    }

    protected float cooldown = 0.25f;
    protected float accum = 0;

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
