package com.mygdx.battlecity.CoreObject;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.battlecity.CoreObject.GameObject.*;
import com.mygdx.battlecity.Game;

public class MainScene extends Scene {
    private TiledMap map;
    private TmxMapLoader maploader;

    public MainScene() {

        maploader = new TmxMapLoader();
        map = maploader.load("Battle_City.tmx");

        for (MapObject object : map.getLayers().get("BrickWall").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            float x = rect.x + 4;
            float y = rect.y + 4;
            x /= Game.PPM;
            y /= Game.PPM;

            Add(new Brick(x, y, Brick.Type.TopLeft));
            Add(new Brick(x, y, Brick.Type.TopRight));
            Add(new Brick(x, y, Brick.Type.BottomLeft));
            Add(new Brick(x, y, Brick.Type.BottomRight));

            //if (type == 0) {
            //    Add("BrickWallTopLeft").SetPosition(x, y);
            //} else if (type == 1) {
            //    Add("BrickWallTopRight").SetPosition(x, y);
            //} else if (type == 2) {
            //    Add("BrickWallBottomLeft").SetPosition(x, y);
            //} else if (type == 3) {
            //    Add("BrickWallBottomRight").SetPosition(x, y);
            //}


            //else if (type == 4) {
            //    Add("Eagle").SetPosition(((RectangleMapObject) object).getRectangle().x + 16,
            //            ((RectangleMapObject) object).getRectangle().y + 16);
            //}
        }

        for (MapObject object : map.getLayers().get("Bound").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            float x = rect.x + 8;
            float y = rect.y + 8;
            x /= Game.PPM;
            y /= Game.PPM;

            Add(new Bound(x, y));
        }

        for (MapObject object : map.getLayers().get("Tree").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            float x = rect.x + 8;
            float y = rect.y;
            x /= Game.PPM;
            y /= Game.PPM;

            Add("Tree").SetPosition(x, y);
        }

        for (MapObject object : map.getLayers().get("Water").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            float x = rect.x + 8;
            float y = rect.y;
            x /= Game.PPM;
            y /= Game.PPM;

            Add(new Water(x, y));
        }


        Add(new Player());
        Add(new BasicTank(10, 20));
        Add(new ArmorTank(30, 20));
        Add(new FastTank(50, 20));
        Add(new PowerTank(60, 20));
        Add(new Spawner());
    }
}
