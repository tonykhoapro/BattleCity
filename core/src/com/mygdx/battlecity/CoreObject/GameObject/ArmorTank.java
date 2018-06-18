package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Game;

public class ArmorTank extends Enemy {
    public ArmorTank(float x, float y) {
        super(new SpriteComponent("ArmorTank", 0.1f, true));
        HP = 5;
        speed = speed / 1.4f;
        cooldownFactor = 1.5f;
        respawnPosition = new Vector2(x, y);
        SetPosition(respawnPosition.x, respawnPosition.y);
    }


}
