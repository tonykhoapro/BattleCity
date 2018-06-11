package com.mygdx.battlecity.CoreObject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Transform;
import com.mygdx.battlecity.Tickable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

public abstract class Actor extends Tickable {

    public final Vector2 GetPosition() {
        return transform.getPosition().cpy();
    }

    public final float GetRotation() {
        return transform.getOrientation().angle();
    }

    public final float GetRotationInRad() {
        return transform.getRotation();
    }

    public final void SetPosition(Vector2 position) {
        transform.setPosition(position);
    }

    public final void SetPosition(float x, float y) {
        SetPosition(new Vector2(x, y));
    }

    public final void SetRotation(float rotation) {
        transform.setRotation(rotation * (float) Math.PI / 180);
    }

    public final void SetRotation(double rotation) {
        transform.setRotation((float) rotation);
    }

    // Class phải có default constructor.
    public final void AddComponent(Class<?> cls) {
        Constructor<?> ctor = null;
        try {
            assert cls != null;
            ctor = cls.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Object object = null;
        try {
            assert ctor != null;
            object = ctor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        assert object != null;
        AddComponent((Component) object);
    }

    public final void AddComponent(Component component) {
        componentList.add(component);
        component.Attach(this);
        if (isActive()) component.OnActivate();
    }

    public final void RemoveComponent(Component component) {
        assert (component.getActor() == this);
        if (isActive()) {
            component.OnDeactivate();
        }
        componentList.remove(component);
        destroyComponents.add(component);
    }

    public final void RemoveComponent(Class<?> cls) {
        @SuppressWarnings("unchecked")
        HashSet<Component> tmpList = (HashSet<Component>) componentList.clone();

        for (Component component : tmpList) {
            if (cls.isInstance(component)) {
                RemoveComponent(component);
            }
        }
    }

    @Override
    public void OnActivate() {
        super.OnActivate();

        @SuppressWarnings("unchecked")
        HashSet<Component> tmpList = (HashSet<Component>) componentList.clone();

        for (Component component : tmpList) {
            component.OnActivate();
        }
    }

    @Override
    public void OnTick(float dt) {
        super.OnTick(dt);
        for (Component component : destroyComponents) {
            component.Deattach();
        }
        destroyComponents.clear();
    }

    @Override
    public void OnDeactivate() {
        super.OnDeactivate();
        @SuppressWarnings("unchecked")
        HashSet<Component> tmpList = (HashSet<Component>) componentList.clone();

        for (Component component : tmpList) {
            component.OnDeactivate();
        }
    }

    public void OnBeginHit(Actor other) {
    }

    public void OnPreHit(Actor other) {
    }

    public void OnEndHit(Actor other) {
    }

    public final Scene getScene() {
        return scene;
    }

    public final void setScene(Scene scene) {
        this.scene = scene;
    }

    private Scene scene;
    private HashSet<Component> componentList = new HashSet<Component>();
    private HashSet<Component> destroyComponents = new HashSet<Component>();
    private Transform transform = new Transform(new Vector2(100.0f, 100.0f), 0);
}
