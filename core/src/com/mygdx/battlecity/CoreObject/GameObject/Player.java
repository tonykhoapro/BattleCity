package com.mygdx.battlecity.CoreObject.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Game;

public class Player extends Tank {

    public Player() {
        super(new SpriteComponent("Player1"));
        cooldown = 0.6f;
        SetPosition(304 / Game.PPM, 144 / Game.PPM);
        respawnPosition = new Vector2(304 / Game.PPM, 144 / Game.PPM);
        respawnRotation = 0;
    }

    @Override
    void Update(float dt) {
        PollInput();
    }

    protected final void PollInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.X) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Shoot();

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
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
