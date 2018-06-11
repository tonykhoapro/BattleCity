package com.mygdx.battlecity.CoreObject;


public abstract class Component {

    public boolean isActive() {
        if (actor == null) {
            return false;
        } else {
            return actor.isActive();
        }
    }

    public Actor getActor() {
        return actor;
    }

    protected final void Attach(Actor actor) {
        assert (this.actor == null);
        this.actor = actor;
        OnAttach();
    }

    public final void Deattach() {
        assert (actor != null);
        actor = null;
    }

    public void OnAttach() {
    }

    public void OnActivate() {
        assert (!isActive() && actor != null);
    }

    public void OnDeactivate() {
        assert (isActive() && actor != null);
    }


    private Actor actor;
}
