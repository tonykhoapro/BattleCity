package com.mygdx.battlecity.System;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.battlecity.CoreObject.SpriteComponent;
import com.mygdx.battlecity.Game;

import java.util.*;

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

        for (SpriteComponent sprite : sortedList) {
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
        //sortedList =(LinkedList<SpriteComponent>) spriteList.clone();
        sortedList = new LinkedList<SpriteComponent>(spriteList);
        Sort();
    }

    public void RemoveSprite(SpriteComponent sprite) {
        spriteList.remove(sprite);
        sortedList = new LinkedList<SpriteComponent>(spriteList);
        Sort();
    }

    public void Sort() {
        Collections.sort(sortedList, new Comparator<SpriteComponent>() {
            @Override
            public int compare(SpriteComponent lhs, SpriteComponent rhs) {
                if (lhs.getzOrder() < rhs.getzOrder()) {
                    return -1;
                } else if (lhs.getzOrder() > rhs.getzOrder()) {
                    return 1;
                } else return 0;
            }
        });
    }

    private HashSet<SpriteComponent> spriteList = new HashSet<SpriteComponent>();
    private SpriteBatch spriteBatch;
    private float counter = 0;

    private LinkedList<SpriteComponent> sortedList = new LinkedList<>();
}
