package com.mygdx.battlecity.CoreObject;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.battlecity.Game;
import com.mygdx.battlecity.TickManager;
import com.mygdx.battlecity.System.RenderSystem;

public class SpriteComponent extends Component {

    public SpriteComponent(String regionName, int zOder) {
        name = regionName;
        animated = false;
        sprite = Game.CreateSprite(regionName);
        assert (sprite != null);
        this.zOrder = zOder;
    }
    public SpriteComponent(String regionName) {
        name = regionName;
        animated = false;
        sprite = Game.CreateSprite(regionName);
        assert (sprite != null);
    }

    public SpriteComponent(String animationName, float frameDuration, boolean looped) {
        name = animationName;
        animated = true;

        animation = new Animation<>(frameDuration,
                Game.CreateSprites(animationName),
                looped ? Animation.PlayMode.LOOP : Animation.PlayMode.NORMAL);

        sprite = animation.getKeyFrame(0);
        assert (sprite != null);
    }

    @Override
    public void OnAttach() {
        sprite.setRotation(getActor().GetRotation());
        sprite.setPosition(getActor().GetPosition().x, getActor().GetPosition().y);
    }

    @Override
    public void OnActivate() {
        super.OnActivate();
        renderSystem.AddSprite(this);
    }

    @Override
    public void OnDeactivate() {
        super.OnDeactivate();
        renderSystem.RemoveSprite(this);
    }

    public final Sprite getSprite() {
        return sprite;
    }

    public final void setSprite(Sprite sprite) {
        this.sprite = sprite;

    }

    public void ResetDrawPositionAndRotation() {
        sprite.setPosition(getActor().GetPosition().x + offset.x - sprite.getWidth() * 0.5f,
                getActor().GetPosition().y + offset.y - sprite.getHeight() * 0.5f);
        sprite.setRotation(getActor().GetRotation());
    }

    public boolean isAnimated() {
        return animated;
    }

    public Animation<Sprite> getAnimation() {
        return animation;
    }

    public Vector2 getOffset() {
        return offset;
    }

    public void setOffset(Vector2 offset) {
        this.offset = offset;
    }

    public String getName() {
        return name;
    }

    public int getzOrder() {
        return zOrder;
    }

    public void setzOrder(int zOrder) {
        this.zOrder = zOrder;
        renderSystem.RemoveSprite(this);
        renderSystem.AddSprite(this);
    }

    private Sprite sprite;
    private boolean animated;
    private Animation<Sprite> animation;
    private Vector2 offset = new Vector2(0, 0);


    private int zOrder = 0;
    private String name;

    private static RenderSystem renderSystem = (RenderSystem) TickManager.getInstance().FindByType(RenderSystem.class);
}
