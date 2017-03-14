package com.trando.dungeoncrawler;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.trando.dungeoncrawler.systems.*;

public class DungeonCrawler extends ApplicationAdapter {
    public static final float PPM = 32;

    Tile[][] tileBoard;

    Body floor;

    Box2DDebugRenderer renderer;

    OrthographicCamera camera;
    World world;
    Vector2 gravity = new Vector2(0, 0f);
    SpriteBatch batch;

    Body body;
    public Sprite img;

    public float width;
    public float height;

    Tile lookTile;

    Engine engine;

    InputHandler inputHandler;

    @Override
    public void create() {

        batch = new SpriteBatch();
        engine = new Engine();
        Box2D.init();
        height = Gdx.graphics.getHeight()/PPM;
        width = Gdx.graphics.getWidth()/PPM;

        img = new Sprite(new Texture("badlogic.jpg"));
        camera = new OrthographicCamera(width, height);

        world = new World(gravity, true);
        renderer = new Box2DDebugRenderer();

        InputMultiplexer inputMultiplexer = new InputMultiplexer();

        inputHandler = new InputHandler();
        inputMultiplexer.addProcessor(inputHandler);

        engine.addSystem(new RenderSystem(batch));
        engine.addSystem(new PlayerControlledSystem(inputHandler));
        engine.addSystem(new CameraFocusSystem(camera));
        engine.addSystem(new AnimationSystem());
        engine.addEntity(new Player(world));


        tileBoard = new Tile[(int) height][(int) width];
        for(int i = 0; i< tileBoard.length; i++){
            for(int j = 0; j< tileBoard[0].length; j++){
                tileBoard[i][j] = new Tile(world, i, j);
                engine.addEntity(tileBoard[i][j]);
            }
        }

        camera.position.set(0, 0, 0);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render(world, camera.combined);
        batch.begin();
        engine.update(Gdx.graphics.getDeltaTime());
        batch.end();
        camera.update();

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    @Override
    public void resize(int width, int height){
        camera.setToOrtho(false, width/PPM, height/PPM);
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
