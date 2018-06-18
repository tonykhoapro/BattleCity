package com.mygdx.battlecity.CoreObject;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.battlecity.CoreObject.GameObject.*;

public class MainScene extends Scene {
    private TiledMap map;
    private TmxMapLoader maploader;

    public MainScene() {

        maploader = new TmxMapLoader();
        map = maploader.load("battlecity.tmx");

        for (MapObject object : map.getLayers().get("Objects").getObjects().getByType(RectangleMapObject.class)) {
            int type = (Integer) object.getProperties().get("Type");

            if (type == 0) {
                Add("BrickWallTopLeft").SetPosition(((RectangleMapObject) object).getRectangle().x + 4,
                        ((RectangleMapObject) object).getRectangle().y + 4);
            } else if (type == 1) {
                Add("BrickWallTopRight").SetPosition(((RectangleMapObject) object).getRectangle().x + 4,
                        ((RectangleMapObject) object).getRectangle().y + 4);
            } else if (type == 2) {
                Add("BrickWallBottomLeft").SetPosition(((RectangleMapObject) object).getRectangle().x + 4,
                        ((RectangleMapObject) object).getRectangle().y + 4);
            } else if (type == 3) {
                Add("BrickWallBottomRight").SetPosition(((RectangleMapObject) object).getRectangle().x + 4,
                        ((RectangleMapObject) object).getRectangle().y + 4);
            } else if (type == 4) {
                Add("Eagle").SetPosition(((RectangleMapObject) object).getRectangle().x + 16,
                        ((RectangleMapObject) object).getRectangle().y + 16);
            }
        }

        for (MapObject object : map.getLayers().get("Bounds").getObjects().getByType(RectangleMapObject.class)) {
            Add(new BoundObject(((RectangleMapObject) object).getRectangle().x + 8,
                    ((RectangleMapObject) object).getRectangle().y + 8));
        }

        Add(new Player());
        Add(new Enemy());
        Add(new Spawner());
    }
}
