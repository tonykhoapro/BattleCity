package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Game;

public class PowerTank extends Enemy {
    public PowerTank(float x, float y) {
        super(new SpriteComponent("PowerTank"));
        HP = 2;
        speed = speed * 1.75f;
        speedbullet = speedbullet * 2.75f;
        cooldownFactor = 0.85f;
        respawnPosition = new Vector2(x, y);
        SetPosition(respawnPosition.x, respawnPosition.y);
    }
}