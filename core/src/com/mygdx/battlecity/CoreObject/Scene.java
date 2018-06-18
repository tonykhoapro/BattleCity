package com.mygdx.battlecity.CoreObject;

import com.mygdx.battlecity.Tickable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

public abstract class Scene {
    public final void Begin() {
        for (Actor actor : actorHashSet) {
            Tickable.Activate(actor);
        }
    }

    public final void End() {
        for (Actor actor : actorHashSet) {
            Tickable.Deactivate(actor);
        }
    }

    protected final void Add(Actor actor) {
        actorHashSet.add(actor);
    }

    // Class phải thuộc package com.mygdx.battlecity.CoreObject.GameObject và có default constructor.
    protected final Actor Add(String name) {
        name = "com.mygdx.battlecity.CoreObject.GameObject." + name;

        Class<?> clazz = null;
        try {
            clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Constructor<?> ctor = null;
        try {
            assert clazz != null;
            ctor = clazz.getConstructor();
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
        Add((Actor) object);
        return (Actor) object;
    }

    protected final void Remove(Actor actor) {
        actorHashSet.remove(actor);
    }

    public HashSet<Actor> getActorHashSet() {
        return actorHashSet;
    }

    private HashSet<Actor> actorHashSet = new HashSet<Actor>();
}

