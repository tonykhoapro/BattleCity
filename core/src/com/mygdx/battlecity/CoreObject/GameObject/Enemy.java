package com.mygdx.battlecity.CoreObject.GameObject;


import com.badlogic.gdx.math.Vector2;
import com.mygdx.battlecity.CoreObject.Actor;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Game;

import java.util.Random;

public class Enemy extends Tank {

    public Enemy(SpriteComponent spriteComponent) {
        super(spriteComponent);
    }

    @Override
    public void Shoot() {
        super.Shoot();
        if (accum == 0) {
            cooldown = ((float) randomShoot.nextInt(5) + 20) / 20;
            if (target != null) {
                cooldown *= 2.0f;
                target = null;
                ChangeDirection();
            }
        }
    }

    private void AttackPlayer(Actor other) {
        int rot = (int) other.GetRotation();
        cooldown *= 0.5f;
        hitBox.SetVelocity(0, 0);
        if (rot == 0) {
            SetRotation(180);
        } else if (rot == 90) {
            SetRotation(-90);
        } else if (rot == -90) {
            SetRotation(90);
        } else if (rot == 180) {
            SetRotation(0);
        }
        //Activate(new Bullet(GetPosition().x, GetPosition().y, (int) GetRotation(), this, speedbullet));
    }

    @Override
    void Update(float dt) {
        Shoot();
        if ((moveTime += dt) >= changeDirTime) {
            moveTime = 0;
            changeDirTime = ((float) randomDirection.nextInt(8) + 12) * changeDirTimeFactor / 24;
            cooldown = changeDirTime * cooldownFactor * (0.5f + (float) randomDirection.nextInt(2) + 32 * changeDirTimeFactor / 30);
            ChangeDirection();
        }
        if (target != null) {
            AttackPlayer(target);
            target = null;
        }
    }

    protected void ChangeDirection() {
        int randomNum = randomDirection.nextInt(4);
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

    @Override
    public void Respawn() {
        if (currentState == normalState) {
            HP--;
            Activate(new Explosion(GetPosition().x, GetPosition().y));

            if (HP <= 0) {
                //Deactivate(this);
                setAlive(false);
                RandomTank();
            }
        }
    }

    private void RandomTank() {
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

    @Override
    public void OnBeginHit(Actor other) {
        super.OnBeginHit(other);
        if (other instanceof Player) {
            target = other;
        } else if (other instanceof Tank) {
            changeDirTime = 0;
        }
    }

    private Actor target = null;

    protected float changeDirTimeFactor = 1;
    protected float cooldownFactor = 1;

    protected int HP;
    private float changeDirTime = 0.5f;
    private float moveTime = 0;
    Random randomShoot = new Random();
    Random randomDirection = new Random();
}
