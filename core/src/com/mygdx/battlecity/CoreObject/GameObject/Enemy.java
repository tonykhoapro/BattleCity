package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.battlecity.CoreObject.SpriteComponent;

import java.util.Random;

public class Enemy extends Tank {

    public Enemy() {
        super(new SpriteComponent("ArmorTank", 0.15f, true));
        SetPosition(250, 385);
        respawnPosition = new Vector2(250, 385);
    }

    @Override
    void Update(float dt) {
        Shoot();


        if ((moveTime += dt) >= changeDirTime) {
            moveTime = 0;

            Random random = new Random();

            //changeDirTime = (float) random.nextInt(12 - 3) + 3 / 20.0f;

            int randomNum = random.nextInt(4);
            if (randomNum == 0) {
                MoveDirection(Direction.Left);
            } else if (randomNum == 1) {
                MoveDirection(Direction.Right);
            } else if (randomNum == 2) {
                MoveDirection(Direction.Up);
            } else if (randomNum == 3) {
                MoveDirection(Direction.Down);
            }
        }
    }

    /*@Override
    public void OnPreHit(Actor other) {
        Tickable.Deactivate(other);
    }*/

    private float changeDirTime = 0.5f;
    private float moveTime = 0;
}
