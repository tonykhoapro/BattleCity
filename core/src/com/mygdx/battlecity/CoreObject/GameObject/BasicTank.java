package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Game;

public class BasicTank extends Enemy {
    public BasicTank(float x, float y) {
        super(new SpriteComponent("BasicTank"));
        HP = 1;
        speed = speed * 0.8f;
        respawnPosition = new Vector2(x, y);
        SetPosition(respawnPosition.x , respawnPosition.y);
    }
}
