package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Game;

public class Brick extends MapObject {
    public enum Type {
        TopLeft,
        TopRight,
        BottomLeft,
        BottomRight
    }

    public Brick(float x, float y, Brick.Type type) {
        RemoveComponent(hitBox);
        hitBox = new HitBox(8 / Game.PPM, 8 / Game.PPM, BodyDef.BodyType.StaticBody);
        AddComponent(hitBox);

        if (type == Type.TopLeft) {
            AddComponent(new SpriteComponent("BrickWallTopLeft"));
            SetPosition(x, y);
        } else if (type == Type.TopRight) {
            AddComponent(new SpriteComponent("BrickWallTopRight"));
            SetPosition(x + 8 / Game.PPM, y);
        } else if (type == Type.BottomLeft) {
            AddComponent(new SpriteComponent("BrickWallBottomLeft"));
            SetPosition(x, y - 8 / Game.PPM);
        } else if (type == Type.BottomRight) {
            AddComponent(new SpriteComponent("BrickWallBottomRight"));
            SetPosition(x + 8 / Game.PPM, y - 8 / Game.PPM);
        }
    }

    @Override
    public void OnTick(float dt) {
        super.OnTick(dt);
        //if(!isAlive())
        //{
        //    Deactivate(this);
        //}
    }

    @Override
    public void OnBeginHit(Actor other) {
        super.OnBeginHit(other);
        if(Bullet.class.isInstance(other))
        {
            setAlive(false);
        }
    }
}
