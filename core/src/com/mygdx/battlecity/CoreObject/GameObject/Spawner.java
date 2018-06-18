package com.mygdx.battlecity.CoreObject.GameObject;

import com.mygdx.battlecity.CoreObject.Actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spawner extends Actor {
    float timeCounter;
    float spawnTime;
    boolean wasSpawn = false;
    Random rand = new Random();
    List<Item> itemList = new ArrayList<Item>();

    public Spawner() {
        spawnTime = rand.nextInt(5) + 5;
    }

    public void spawn() {
        int itemNum = rand.nextInt(5) + 15;
        for (int i = 1; i < itemNum; i++) {
            switch (rand.nextInt(5)) {
                case 0:
                    itemList.add(new Grenade());
                    break;
                case 1:
                    itemList.add(new Helmet());
                    break;
                case 2:
                    itemList.add(new Star());
                    break;
                case 3:
                    itemList.add(new Timer());
                    break;
                default:
                    itemList.add(new Shovel());
                    break;
            }
        }
        for(Item item : itemList){
            Activate(item);
        }
    }

    @Override
    public void OnTick(float dt) {
        super.OnTick(dt);
        timeCounter += dt;
        if (timeCounter >= spawnTime && !wasSpawn) {
            timeCounter = 0;
            this.spawn();
            wasSpawn = true;

//            RemoveComponent(animationItem);
//            RemoveComponent(hitBox);
        }
    }
}
