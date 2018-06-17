package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

public class BaseObject extends Actor {

    public enum TState {
        appearing,//Xuất hiện
        running, //Di chuyển
        explosion,	//Đang chết
    }
    public TState state = TState.appearing;
    public HitBox hitBox = new HitBox(30, 30, BodyDef.BodyType.DynamicBody);
    public SpriteComponent spriteRun;
    public SpriteComponent AnimationAppear = new SpriteComponent("Twinkle", 0.1f, true);
    public SpriteComponent AnimationExplosion = new SpriteComponent("Explosion", 0.1f, true);

    public float speed;
    public float statetime;

    @Override
    public void OnTick(float dt) {
        super.OnTick(dt);
        ChangeState(state, dt);


    }

    public void ChangeState(TState state, float dt) {
    }
}
