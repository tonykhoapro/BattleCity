package com.mygdx.battlecity.CoreObject.GameObject;


import com.badlogic.gdx.math.Vector2;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Game;

import java.util.Random;

public class Enemy extends Tank {

    public Enemy(SpriteComponent spriteComponent) {
        super(spriteComponent);
    }

    @Override
    void Update(float dt) {
        Shoot();
        if ((moveTime += dt) >= changeDirTime) {
            moveTime = 0;

            Random random = new Random();
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

    @Override
    public void Respawn() {
        if (currentState == normalState) {
            HP --;
            Activate(new Explosion(GetPosition().x, GetPosition().y));

            if (HP <= 0){
                Deactivate(this);
                RandomTank();

            }
        }
    }

    private void RandomTank(){
        Random random = new Random();
        int randomNum = random.nextInt(4);
        if (randomNum == 0) {
            Activate(new ArmorTank(respawnPosition.x, respawnPosition.y));
        } else if (randomNum == 1) {
            Activate(new BasicTank(respawnPosition.x, respawnPosition.y));
        } else if (randomNum == 2) {
            Activate(new FastTank(respawnPosition.x, respawnPosition.y));
        } else if (randomNum == 3) {
            Activate(new PowerTank(respawnPosition.x, respawnPosition.y));
        }

    }
    protected int HP;
    private float changeDirTime = 0.5f;
    private float moveTime = 0;
}
