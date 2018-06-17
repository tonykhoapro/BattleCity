package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.Component;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Tickable;

public class BaseObject extends Actor {

    HitBox hitBox = new HitBox(26, 26, BodyDef.BodyType.DynamicBody);

    SpriteComponent normalState = new SpriteComponent("Player1");
    SpriteComponent appearingState = new SpriteComponent("Twinkle", 0.06f, true);
    SpriteComponent protectedState = new SpriteComponent("Shield", 0.1f, true);

    SpriteComponent currentState = appearingState;

    float speed = 120;
    boolean locked = true;

    static final float APPEAR_TIME = 1.6f;
    float appearAccumTime = 0;

    static final float PROTECTED_TIME = 1;
    float protectedAccumTime = 0;


    public BaseObject() {
        AddComponent(hitBox);
        AddComponent(currentState);
    }

    @Override
    public void OnTick(float dt) {
        super.OnTick(dt);

        if (currentState.getName() == "Player1") {
            PollInput();
        } else if (currentState.getName() == "Shield") {
            OnProtected(dt);
        } else if (currentState.getName() == "Twinkle") {
            OnApperaing(dt);
        }

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

    public void Respawn()
    {
        Activate(new Explosion(GetPosition().x, GetPosition().y));

        RemoveComponent(currentState);
        currentState = appearingState;
        AddComponent(currentState);
        SetPosition(205, 64);
        SetRotation(0);
        hitBox.SetVelocity(0, 0);
    }


    protected final void PollInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {

            Activate(new Bullet(GetPosition().x, GetPosition().y, (int)GetRotation()));


        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            hitBox.SetVelocity(-speed, 0);
            SetRotation(90);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            hitBox.SetVelocity(speed, 0);
            SetRotation(-90);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            hitBox.SetVelocity(0, speed);
            SetRotation(0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            hitBox.SetVelocity(0, -speed);
            SetRotation(180);
        } else {
            hitBox.SetVelocity(0, 0);
        }
    }


    /*@Override
    public void OnBeginHit(Actor other) {
        super.OnBeginHit(other);
        Deactivate(other);
    }*/


}
