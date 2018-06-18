package com.mygdx.battlecity.CoreObject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.battlecity.Game;
import com.mygdx.battlecity.System.WorldSimulator;
import com.mygdx.battlecity.TickManager;

public class HitBox extends Component {
    public HitBox(float width, float height, BodyDef.BodyType bodyType) {
        Init(width, height, bodyType, false, false, (short)-1);
    }



    public HitBox(float width, float height, BodyDef.BodyType bodyType, boolean isSensor) {
        Init(width, height, bodyType, isSensor, false, (short)-1);
    }

    public HitBox(float width, float height, BodyDef.BodyType bodyType, boolean isSensor, boolean isBullet) {
        Init(width, height, bodyType, isSensor, isBullet, (short)-1);
    }

    public HitBox(float width, float height, BodyDef.BodyType bodyType, boolean isSensor, boolean isBullet, int maskBits) {
        Init(width, height, bodyType, isSensor, isBullet, (short)maskBits);
    }

    private void Init(float width, float height, BodyDef.BodyType bodyType, boolean isSensor, boolean isBullet, short maskBits) {
        halfSize.set(width / 2 , height / 2);

        bodyDef.type = bodyType;
        bodyDef.bullet = isBullet;

        shape.setAsBox(halfSize.x, halfSize.y);

        fixtureDef.filter.maskBits = maskBits;
        fixtureDef.shape = shape;
        fixtureDef.density = 0;
        fixtureDef.restitution = 0.005f;
        fixtureDef.friction = 0;
        fixtureDef.isSensor = isSensor;
    }

    public void SetPosition(float x, float y) {
        pending = new Transform(new Vector2(x, y), body.getAngle());
        if (!isActive()) body.setTransform(x, y , body.getAngle());
    }

    public Vector2 GetPosition() {
        return body.getTransform().getPosition().cpy();
    }

    @Override
    public void OnAttach() {
    }

    @Override
    public void OnActivate() {
        super.OnActivate();
        bodyDef.position.set(getActor().GetPosition().x, getActor().GetPosition().y);
        bodyDef.angle = getActor().GetRotation();
        worldSimulator.AddHitBoxToWorld(this);
    }

    @Override
    public void OnDeactivate() {
        super.OnDeactivate();
        worldSimulator.RemoveHitBoxFromWorld(this);
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        shape.dispose();
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }

    public PolygonShape getShape() {
        return shape;
    }

    public void SetVelocity(float x, float y) {
        body.setLinearVelocity(x , y );
    }

    public Vector2 GetVelocity() {
        return body.getLinearVelocity().cpy();
    }

    private Body body;
    BodyDef bodyDef = new BodyDef();
    FixtureDef fixtureDef = new FixtureDef();
    PolygonShape shape = new PolygonShape();
    Vector2 halfSize = new Vector2(0, 0);

    public Transform getPending() {
        return pending;
    }

    public void setPending(Transform pending) {
        this.pending = pending;
    }

    Transform pending = null;

    private static WorldSimulator worldSimulator = (WorldSimulator) TickManager.getInstance().FindByType(WorldSimulator.class);
}
