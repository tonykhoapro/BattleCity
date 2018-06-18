package com.mygdx.battlecity.System;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Game;

import java.util.HashSet;

public final class RenderSystem extends System {

    public RenderSystem(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    @Override
    public void OnActivate() {
        super.OnActivate();
    }

    @Override
    public void OnDeactivate() {
        super.OnDeactivate();
    }

    @Override
    public void OnTick(float dt) {
        super.OnTick(dt);

        for (SpriteComponent sprite : spriteList) {
            assert (sprite.getSprite() != null);

            if (sprite.isAnimated()) {
                sprite.setSprite(sprite.getAnimation().getKeyFrame(counter));
            }

            sprite.ResetDrawPositionAndRotation();


            sprite.getSprite().draw(spriteBatch);
        }
        counter += dt;
    }

    public void AddSprite(SpriteComponent sprite) {
        spriteList.add(sprite);
    }

    public void RemoveSprite(SpriteComponent sprite) {
        spriteList.remove(sprite);
    }

    private HashSet<SpriteComponent> spriteList = new HashSet<SpriteComponent>();
    private SpriteBatch spriteBatch;
    private float counter = 0;
}
