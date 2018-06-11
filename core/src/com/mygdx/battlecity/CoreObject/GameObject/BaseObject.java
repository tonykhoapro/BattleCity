package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Tickable;

public class BaseObject extends Actor {
    HitBox hitBox = new HitBox(32, 32, BodyDef.BodyType.DynamicBody);

    public BaseObject() {
        AddComponent(new SpriteComponent("Player1"));
        AddComponent(hitBox);
    }

    @Override
    public void OnTick(float dt) {
        super.OnTick(dt);
        PollInput();
    }

    protected final void PollInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            Bullet bullet = new Bullet(GetPosition().x, GetPosition().y, (int)GetRotation());

            Tickable.Activate(bullet);
            //bullet.Fire(GetRotation(), GetPosition().x, GetPosition().y);

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

    float speed = 120;
    /*@Override
    public void OnBeginHit(Actor other) {
        super.OnBeginHit(other);
        Deactivate(other);
    }*/
}
