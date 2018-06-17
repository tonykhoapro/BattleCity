package com.mygdx.battlecity.CoreObject.GameObject;

import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Tickable;

import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

public class Enemy extends BaseObject {
    SpriteComponent spriteBasicTank = new SpriteComponent("BasicTank");
    SpriteComponent spriteArmorTank = new SpriteComponent("ArmorTank");
    SpriteComponent spriteFastTank = new SpriteComponent("FastTank");

    SpriteComponent spriteItem;
    SpriteComponent spriteBasicTankRed = new SpriteComponent("BasicTankRed");
    SpriteComponent spriteArmorTankRed = new SpriteComponent("ArmorTankRed");
    SpriteComponent spriteFastTankRed = new SpriteComponent("FastTankRed");

    int DropItem;
    float TimeMove;
    float TimeChangeMove;
    int randMove;
    Random rand = new Random();
    public Enemy() {
        spriteRun = spriteBasicTank;
        spriteItem = spriteBasicTankRed;
        AddComponent(AnimationAppear);
        TimeMove = 0.0f;
        TimeChangeMove = 0.0f;
        DropItem = (rand.nextInt(999) % 7) + 1;
        speed = 100;
        SetPosition(110, 90);
    }

    //
    protected final void AI(float dt) {
        TimeMove += dt;
        if (TimeMove >= TimeChangeMove)
        {
            //reset time move
            TimeMove = 0.0f;
            TimeChangeMove = (1000 + rand.nextInt(1500))/1000;;

            //ngẫu nhiên hướng di chuyển mới
            randMove = rand.nextInt(999) % 4;
        }//end if

        switch (randMove)
        {
            case 0: {
                hitBox.SetVelocity(-speed, 0);
                SetRotation(90);
            } break;

            case 1:{
                hitBox.SetVelocity(speed, 0);
                SetRotation(-90);
            } break;

            case 2:{
                hitBox.SetVelocity(0, speed);
                SetRotation(0);
            } break;

            case 3:{
                hitBox.SetVelocity(0, -speed);
                SetRotation(180);
            }
        }//end switch
    }

    //tạo Item khi bị bắn
    protected final void NewItem() {

        switch (DropItem){
            case 1:{//Helmet
                ItemHelmet Helmet = new ItemHelmet();
                Helmet.SetPosition(GetPosition().x, GetPosition().y);
                Tickable.Activate(Helmet);
            } break;

            case 2:{//Grenade
                Grenade grenade = new Grenade();
                grenade.SetPosition(GetPosition().x, GetPosition().y);
                Tickable.Activate(grenade);
            } break;
        }

        DropItem = (rand.nextInt(999) % 7) + 1;
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

                    RemoveComponent(AnimationAppear);
                    AddComponent(spriteRun);
                    AddComponent(hitBox);

                    if (DropItem != 0)
                        AddComponent(spriteItem);
                }
            } break;

            case running:{
                AI(dt);
            } break;

            case explosion:{
                statetime+=dt/2;
                if (AnimationExplosion.getAnimation().isAnimationFinished(statetime)) {

                    this.state = TState.appearing;
                    statetime = 0.0f;
                    RemoveComponent(AnimationExplosion);
                    AddComponent(AnimationAppear);
                    TimeMove = 0.0f;
                    TimeChangeMove = 0.0f;
                    NewItem();
                }
            } break;
        }
    }

    @Override
    public void OnBeginHit(Actor other) {
        super.OnBeginHit(other);

        //Enemy chạm đạn
        if (Bullet.class.isInstance(other)) {
            this.state = TState.explosion;
            statetime = 0.0f;

            RemoveComponent(hitBox);
            RemoveComponent(spriteRun);
            if (DropItem != 0)
                RemoveComponent(spriteItem);
            AddComponent(AnimationExplosion);
        }
    }
}
