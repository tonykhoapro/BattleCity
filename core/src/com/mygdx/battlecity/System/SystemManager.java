package com.mygdx.battlecity.System;

import java.util.ArrayList;
import java.util.List;

public final class SystemManager {
    private List<System> systemList = new ArrayList<System>();

    public void AddSystem(System system) {
        systemList.add(system);
    }

    public void Init() {
        for (System system : systemList) {
            system.OnActivate();
        }

    }

    public void Shutdown() {
        for (System system : systemList) {
            system.OnDeactivate();
        }
    }

    public void RunMainLoopOnce(float dt) {
        for (System system : systemList) {
            system.OnTick(dt);
        }
    }

    public System GetSystemAs(Class<?> cls) {

        for (System system : systemList) {
            if (cls.isInstance(system)) {

                return system;
            }
        }


        return null;
    }
}
