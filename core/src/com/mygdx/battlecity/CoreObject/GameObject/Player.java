package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Game;

public class Player extends Tank {

    public Player() {
        super(new SpriteComponent("Player4"));
        SetPosition(205 / Game.PPM, 64 / Game.PPM);
        //SetPosition(205 / Game.PPM, 64 / Game.PPM);
        respawnPosition = new Vector2(GetPosition());
        respawnRotation = 0;
    }

    @Override
    void Update(float dt) {
        PollInput();
    }

    protected final void PollInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            Shoot();
            //Activate(new Bullet(GetPosition().x, GetPosition().y, (int) GetRotation()));

        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            MoveDirection(Direction.Left);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            MoveDirection(Direction.Right);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            MoveDirection(Direction.Up);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            MoveDirection(Direction.Down);
        } else {
            hitBox.SetVelocity(0, 0);
        }
    }
}
