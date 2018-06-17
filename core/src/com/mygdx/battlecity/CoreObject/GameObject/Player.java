package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Tickable;

public class Player extends BaseObject{
    SpriteComponent spritePlayer1 = new SpriteComponent("Player1");
    SpriteComponent spritePlayer2 = new SpriteComponent("Player2");
    SpriteComponent spritePlayer3 = new SpriteComponent("Player3");
    SpriteComponent spritePlayer4 = new SpriteComponent("Player4");

    SpriteComponent AnimationShield = new SpriteComponent("Shield", 0.1f, true);

    boolean isShield;
    float TimeShield;
    int level;
    int life;


    public Player() {
        spriteRun = spritePlayer1;
        AddComponent(AnimationAppear);
        isShield = false;
        TimeShield = 3.0f;
        statetime = 0;
        level = 1;
        life = 2;
        speed = 100;
    }

    //Run Shield
    protected final void Shield(float dt) {
        if (!isShield)
            return;
        TimeShield -= dt;
        if (TimeShield < 0.0f) {
            RemoveComponent(AnimationShield);
            isShield = false;
        }
    }

    //KeyHandle
    protected final void PollInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            Bullet bullet = new Bullet(GetPosition().x, GetPosition().y, (int)GetRotation());
            Tickable.Activate(bullet);
            //bullet.Fire(GetRotation(), GetPosition().x, GetPosition().y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
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

    @Override
    public void ChangeState(TState state, float dt) {
        super.ChangeState(state, dt);
        switch (state){
            case appearing:{
                //
                statetime+=dt/3;
                if (AnimationAppear.getAnimation().isAnimationFinished(statetime))
                {
                    this.state = TState.running;
                    statetime = 0.0f;
                    isShield = true;
                    TimeShield = 3.0f;

                    RemoveComponent(AnimationAppear);
                    AddComponent(spriteRun);
                    AddComponent(AnimationShield);
                    AddComponent(hitBox);
                }
            } break;

            case running:{
                PollInput();
                Shield(dt);
            } break;

            case explosion:{
                statetime+=dt/2;
                if (AnimationExplosion.getAnimation().isAnimationFinished(statetime))
                {
                    if (life < 0){
                        Deactivate(this);
                    }
                    else {
                        this.state = TState.appearing;
                        statetime = 0.0f;
                        RemoveComponent(AnimationExplosion);
                        AddComponent(AnimationAppear);
                    }
                }
            } break;
        }
    }

    @Override
    public void OnBeginHit(Actor other) {
        super.OnBeginHit(other);
        //Player chạm đạn
        if (Bullet.class.isInstance(other)) {
            if (!isShield){
                this.state = TState.explosion;
                statetime = 0.0f;

                RemoveComponent(hitBox);
                RemoveComponent(spriteRun);
                AddComponent(AnimationExplosion);
            }
        }
        //Ăn ItemHelmet
        if (ItemHelmet.class.isInstance(other)) {
            if (!isShield)
                AddComponent(AnimationShield);
            TimeShield = 10.0f;
            isShield = true;
        }
        //Chạm Enemy
        if (Enemy.class.isInstance(other)) {
            hitBox.SetVelocity(0, 0);
        }
    }
}
