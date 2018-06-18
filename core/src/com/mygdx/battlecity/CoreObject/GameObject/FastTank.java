package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Game;

public class FastTank extends Enemy {
    public FastTank(float x, float y) {
        super(new SpriteComponent("FastTank"));
        HP = 2;
        changeDirTimeFactor = 0.2f;
        speedbullet = speedbullet * 1.75f;
        cooldownFactor = 0.9f;
        speed = speed * 16;
        respawnPosition = new Vector2(x, y);
        SetPosition(respawnPosition.x, respawnPosition.y);
    }
}