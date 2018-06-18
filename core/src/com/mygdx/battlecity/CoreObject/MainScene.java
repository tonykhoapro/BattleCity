package com.mygdx.battlecity.CoreObject;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.battlecity.CoreObject.GameObject.BoundObject;
import com.mygdx.battlecity.CoreObject.GameObject.Enemy;
import com.mygdx.battlecity.CoreObject.GameObject.Player;
import com.mygdx.battlecity.Game;

public class MainScene extends Scene {
    private TiledMap map;
    private TmxMapLoader maploader;

    public MainScene() {

        maploader = new TmxMapLoader();
        map = maploader.load("battlecity.tmx");

        for (MapObject object : map.getLayers().get("Objects").getObjects().getByType(RectangleMapObject.class)) {
            int type = (Integer) object.getProperties().get("Type");

            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            float x = rect.x + 4;
            float y = rect.y + 4;
            x /= Game.PPM;
            y /= Game.PPM;

            if (type == 0) {
                Add("BrickWallTopLeft").SetPosition(x, y);
            } else if (type == 1) {
                Add("BrickWallTopRight").SetPosition(x, y);
            } else if (type == 2) {
                Add("BrickWallBottomLeft").SetPosition(x, y);
            } else if (type == 3) {
                Add("BrickWallBottomRight").SetPosition(x, y);
            }
            //else if (type == 4) {
            //    Add("Eagle").SetPosition(((RectangleMapObject) object).getRectangle().x + 16,
            //            ((RectangleMapObject) object).getRectangle().y + 16);
            //}
        }

        for (MapObject object : map.getLayers().get("Bounds").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            float x = rect.x + 8;
            float y = rect.y + 8;
            x /= Game.PPM;
            y /= Game.PPM;

            Add(new BoundObject(x, y));

        }


        Add(new Player());
        Add(new Enemy());
    }
}
