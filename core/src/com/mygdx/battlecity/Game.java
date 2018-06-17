package com.mygdx.battlecity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.battlecity.CoreObject.MainScene;
import com.mygdx.battlecity.System.RenderSystem;
import com.mygdx.battlecity.System.SceneManager;
import com.mygdx.battlecity.System.WorldSimulator;

public class Game extends ApplicationAdapter {
    private SpriteBatch batch;
    private static Texture img;
    private static TextureAtlas textureAtlas;
    private static Array<TextureRegion> textureRegionArray;

    public static int WIDTH = 512;
    public static int HEIGHT = 512;

    public static float PPM = 1;

    private FPSLogger fpsLogger = new FPSLogger();

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("sprite.bmp");
        textureAtlas = new TextureAtlas("Atlas/BattleCityAtlas.atlas");
        textureRegionArray = new Array<TextureRegion>(textureAtlas.getRegions());


        Tickable.Activate(new WorldSimulator(batch));
        Tickable.Activate(new RenderSystem(batch));
        Tickable.Activate(new SceneManager(new MainScene()));


        TickManager.getInstance().BeginTick();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        TickManager.getInstance().Tick(Gdx.graphics.getDeltaTime());

        batch.end();

        //fpsLogger.log();
    }

    @Override
    public void dispose() {
        TickManager.getInstance().EndTick();
        batch.dispose();
        img.dispose();
        textureAtlas.dispose();
    }

    public static Sprite CreateSprite(String regionName) {
        for (TextureRegion textureRegion : textureRegionArray) {
            if (textureRegion.toString().equals(regionName)) {
                return new Sprite(new TextureRegion(textureRegion));
            }
        }
        return null;
    }

    public static Array<Sprite> CreateSprites(String keyName) {
        Array<Sprite> sprites = new Array<Sprite>();

        for (TextureRegion textureRegion : textureRegionArray) {
            if (textureRegion.toString().contains((keyName))) {
                sprites.add(new Sprite(new TextureRegion(textureRegion)));
            }
        }

        return sprites;
    }
}
