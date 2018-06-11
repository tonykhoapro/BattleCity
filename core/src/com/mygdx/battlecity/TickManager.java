package com.mygdx.battlecity;

import com.badlogic.gdx.utils.Array;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public final class TickManager {

    private enum EState {
        UNIDENTIFIED,
        BEGIN_TICK,
        TICKING,
        END_TICK
    }

    private TickManager() {
    }

    public static TickManager getInstance() {
        return instance;
    }

    // Gọi OnActivate theo thứ tự được add vào list.
    public void BeginTick() {
        assert (state == EState.UNIDENTIFIED);
        state = EState.BEGIN_TICK;

        @SuppressWarnings("unchecked")
        HashSet<Tickable> tmpList = (HashSet<Tickable>) tickableList.clone();

        for (Tickable tickable : tmpList) {
            tickable.OnActivate();
        }
    }

    public void Tick(float dt) {
        assert (state == EState.TICKING || state == EState.BEGIN_TICK);
        state = EState.TICKING;

        @SuppressWarnings("unchecked")
        HashSet<Tickable> tmpList = (HashSet<Tickable>) tickableList.clone();

        for (Tickable tickable : tmpList) {
            tickable.OnTick(dt);
        }
    }

    // Gọi OnDeactivate theo thứ tự ngược lại khi được add vào list
    // và xóa hết các phần tử trong list.
    public void EndTick() {
        assert (state == EState.TICKING || state == EState.BEGIN_TICK);
        state = EState.END_TICK;

        LinkedList<Tickable> tmpList = new LinkedList<Tickable>();

        tmpList.addAll(tickableList);
        Collections.reverse(tmpList);

        for (Tickable tickable : tmpList) {
            RemoveTickable(tickable);
        }

        tickableList = null;
        state = EState.UNIDENTIFIED;
    }

    public void AddTickable(Tickable tickable) {
        tickableList.add(tickable);
        if (state == EState.BEGIN_TICK || state == EState.TICKING) tickable.OnActivate();
    }

    public void RemoveTickable(Tickable tickable) {
        if (state != EState.UNIDENTIFIED) tickable.OnDeactivate();
        tickableList.remove(tickable);
    }

    public Tickable FindByType(Class<?> cls) {
        for (Tickable tickable : tickableList) {
            if (cls.isInstance(tickable)) {
                return tickable;
            }
        }
        return null;
    }

    public Array<Tickable> FindAllByType(Class<?> cls) {
        Array<Tickable> tickables = new Array<Tickable>();

        for (Tickable tickable : tickableList) {
            if (cls.isInstance(tickable)) {
                tickables.add(tickable);
            }
        }
        return tickables;
    }

    public final boolean isActive() {
        return state == EState.TICKING || state == EState.BEGIN_TICK;
    }

    private static final TickManager instance = new TickManager();
    private EState state = EState.UNIDENTIFIED;
    private HashSet<Tickable> tickableList = new HashSet<Tickable>();
}
