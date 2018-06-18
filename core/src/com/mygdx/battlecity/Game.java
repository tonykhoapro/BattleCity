package com.mygdx.battlecity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
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
    private static TextureAtlas textureAtlas;
    private static Array<TextureRegion> textureRegionArray;

    private static Camera camera;

    public static int WIDTH = 1280;
    public static int HEIGHT = 720;

    public static float PPM = 16;

    private FPSLogger fpsLogger = new FPSLogger();

    @Override
    public void create() {
        batch = new SpriteBatch();

        int CAMERA_WIDTH = WIDTH / 16;
        int CAMERA_HEIGHT = HEIGHT / 16;

        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();


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

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        TickManager.getInstance().Tick(Gdx.graphics.getDeltaTime());

        batch.end();

        //fpsLogger.log();
    }

    @Override
    public void dispose() {
        TickManager.getInstance().EndTick();
        batch.dispose();
        textureAtlas.dispose();
    }

    public static Sprite CreateSprite(String regionName) {
        for (TextureRegion textureRegion : textureRegionArray) {
            if (textureRegion.toString().equals(regionName)) {
                Sprite s = new Sprite(new TextureRegion(textureRegion));
                s.setSize(s.getWidth() / Game.PPM, s.getHeight() / Game.PPM);
                s.setOrigin(s.getWidth() / 2, s.getHeight() / 2);
                return s;
            }
        }
        return null;
    }

    public static Array<Sprite> CreateSprites(String keyName) {
        Array<Sprite> sprites = new Array<Sprite>();

        for (TextureRegion textureRegion : textureRegionArray) {
            if (textureRegion.toString().contains((keyName))) {
                Sprite s = new Sprite(new TextureRegion(textureRegion));
                s.setSize(s.getWidth() / Game.PPM, s.getHeight() / Game.PPM);
                s.setOrigin(s.getWidth() / 2, s.getHeight() / 2);
                sprites.add(s);
            }
        }

        return sprites;
    }
}
