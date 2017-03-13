package com.trando.dungeoncrawler;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.trando.dungeoncrawler.systems.*;

public class DungeonCrawler extends ApplicationAdapter implements InputProcessor {
    public static final float PPM = 32;
    Array<Test> bodies;

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
        bodies = new Array<Test>();
        camera = new OrthographicCamera(width, height);

        world = new World(gravity, true);
        renderer = new Box2DDebugRenderer();

        InputMultiplexer inputMultiplexer = new InputMultiplexer();

        inputHandler = new InputHandler();
        inputMultiplexer.addProcessor(inputHandler);

        engine.addSystem(new RenderSystem(batch));
        engine.addSystem(new PlayerControlledSystem(inputHandler));
        engine.addSystem(new CameraFocusSystem(camera));
        engine.addEntity(new Player(world));


        tileBoard = new Tile[(int) height][(int) width];
        for(int i = 0; i< tileBoard.length; i++){
            for(int j = 0; j< tileBoard[0].length; j++){
                tileBoard[i][j] = new Tile(world, i, j);
                engine.addEntity(tileBoard[i][j]);
            }
        }

/*

        body = new Test(world).body;
        floor = new Floor(world).body;

        for(int i = 0; i< 10; i++){
           // bodies.add(new Test(world));
        }



        lookTile = tileBoard[10][10];
*/

        camera.position.set(0, 0, 0);
        inputMultiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render() {
        //System.out.println("running");
        batch.setProjectionMatrix(camera.combined);
      /*  img.setPosition(body.getPosition().x, body.getPosition().y);
        System.out.println(body.getAngularVelocity());
        img.setRotation(body.getAngle());*/

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render(world, camera.combined);
        batch.begin();
        engine.update(Gdx.graphics.getDeltaTime());
        /*batch.draw(img, body.getPosition().x - (img.getWidth()/PPM)/2, body.getPosition().y - (img.getHeight()/PPM)/2, (img.getWidth()/PPM)/2,img.getHeight()/2/PPM, img.getWidth()/PPM, img.getHeight()/PPM,
                   1,1, body.getAngle());
        for(Test test: bodies){
            Vector2 vec = new Vector2();
            vec.setToRandomDirection();

            test.body.applyLinearImpulse(vec, test.body.getPosition(), true);
            batch.draw(test.img, test.body.getPosition().x - (test.img.getWidth()/PPM)/2, test.body.getPosition().y - (test.img.getHeight()/PPM)/2, (test.img.getWidth()/PPM)/2,
                       test.img.getHeight()/2/PPM, test.img.getWidth()/PPM, test.img.getHeight()/PPM,
                       1,1, body.getAngle());
        }*/
        batch.end();
        camera.update();

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    @Override
    public void resize(int width, int height){
        camera.setToOrtho(false, width/PPM, height/PPM);
//        camera.position.set(lookTile.col, lookTile.row, 0);
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                camera.translate(0,5);
               // body.applyLinearImpulse(new Vector2(0, 15f), new Vector2(0, 0), true);
                break;
            case Input.Keys.DOWN:
                camera.translate(0, -5);
                //body.applyLinearImpulse(new Vector2(0, -5f), new Vector2(0, 0), true);
                break;
            case Input.Keys.RIGHT:
                camera.translate(5,0);
                //body.applyLinearImpulse(new Vector2(5f, 0),new Vector2(0, 0), true);
                break;
            case Input.Keys.LEFT:
                camera.translate(-5,0);
                //body.applyLinearImpulse(new Vector2(-5f, 0),new Vector2(0, 0), true);
                break;
            case Input.Keys.A:
                //doesn't have rotation fixture so cannot rotate
                body.setAngularVelocity(0f);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        /*body.applyForce(new Vector2(500f, 500f), new Vector2(0, 0), true);
        System.out.println("HELlo");
        return true;*/
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
