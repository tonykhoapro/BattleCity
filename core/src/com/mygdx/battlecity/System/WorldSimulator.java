package com.mygdx.battlecity.System;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.battlecity.CoreObject.HitBox;
import com.mygdx.battlecity.Game;

import java.util.HashSet;

public class WorldSimulator extends System implements ContactListener {

    public WorldSimulator(SpriteBatch batch) {
        spriteBatch = batch;
        world.setContactListener(this);
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public void OnActivate() {
        super.OnActivate();
    }

    @Override
    public void OnDeactivate() {
        super.OnDeactivate();

        world.dispose();
        b2dr.dispose();
    }

    @Override
    public void OnTick(float dt) {
        super.OnTick(dt);
        //b2dr.render(world, spriteBatch.getProjectionMatrix());
        world.getBodies(bodyArray);

        world.step(1 / 60.0f, 8, 4);

        for (Body body : bodyArray) {
            if (body.getType() != BodyDef.BodyType.StaticBody) {
                HitBox hitBox = (HitBox) body.getUserData();
                if (hitBox.getPending() == null) {
                    //hitBox.getActor().SetPosition(body.getPosition());
                    hitBox.getActor().getTransform().setPosition(body.getPosition());
                } else {
                    body.setTransform(hitBox.getPending().getPosition(), hitBox.getPending().getRotation());
                    body.setLinearVelocity(new Vector2(0, 0));
                    hitBox.setPending(null);
                }
            }
        }

        for (HitBox hitBox : destroyedHitBoxes) {
            world.destroyBody(hitBox.getBody());
        }
        destroyedHitBoxes.clear();
    }

    public void AddHitBoxToWorld(HitBox hitBox) {

        hitBox.setBody(world.createBody(hitBox.getBodyDef()));
        hitBox.getBody().createFixture(hitBox.getFixtureDef());
        hitBox.getBody().setUserData(hitBox);
    }

    public void RemoveHitBoxFromWorld(HitBox hitBox) {
        destroyedHitBoxes.add(hitBox);
    }

    private World world = new World(new Vector2(0, 0), true);

    private Array<Body> bodyArray = new Array<Body>();
    private HashSet<HitBox> destroyedHitBoxes = new HashSet<HitBox>();

    private Box2DDebugRenderer b2dr = new Box2DDebugRenderer();
    private SpriteBatch spriteBatch;


    @Override
    public void beginContact(Contact contact) {
        HitBox hitBoxA = (HitBox) contact.getFixtureA().getBody().getUserData();
        HitBox hitBoxB = (HitBox) contact.getFixtureB().getBody().getUserData();
        hitBoxA.getActor().OnBeginHit(hitBoxB.getActor());
        hitBoxB.getActor().OnBeginHit(hitBoxA.getActor());
    }

    @Override
    public void endContact(Contact contact) {
        HitBox hitBoxA = (HitBox) contact.getFixtureA().getBody().getUserData();
        HitBox hitBoxB = (HitBox) contact.getFixtureB().getBody().getUserData();
        hitBoxA.getActor().OnEndHit(hitBoxB.getActor());
        hitBoxB.getActor().OnEndHit(hitBoxA.getActor());
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        HitBox hitBoxA = (HitBox) contact.getFixtureA().getBody().getUserData();
        HitBox hitBoxB = (HitBox) contact.getFixtureB().getBody().getUserData();
        hitBoxA.getActor().OnPreHit(hitBoxB.getActor());
        hitBoxB.getActor().OnPreHit(hitBoxA.getActor());
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
