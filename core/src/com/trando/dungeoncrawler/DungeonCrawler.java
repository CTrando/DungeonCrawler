package com.trando.dungeoncrawler;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class DungeonCrawler extends ApplicationAdapter implements InputProcessor {
    public static final float PPM = 16;
    Array<Test> bodies;

    Body floor;

    Box2DDebugRenderer renderer;

    OrthographicCamera camera;
    World world;
    Vector2 gravity = new Vector2(0, -9.81f);
    SpriteBatch batch;

    Body body;
    public Sprite img;

    @Override
    public void create() {
        Box2D.init();
        img = new Sprite(new Texture("badlogic.jpg"));
        bodies = new Array<Test>();
        camera = new OrthographicCamera(Gdx.graphics.getWidth()/PPM, Gdx.graphics.getHeight()/PPM);

        world = new World(gravity, true);
        renderer = new Box2DDebugRenderer();

        body = new Test(world).body;
        floor = new Floor(world).body;

        for(int i = 0; i< 10; i++){
            //bodies.add(new Test(world));
        }

        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        System.out.println(body.getPosition().toString());
        batch.setProjectionMatrix(camera.combined);
        img.setPosition(body.getPosition().x, body.getPosition().y);
        float x = body.getPosition().x;
        float y = body.getPosition().y;

        camera.position.set(x , y , 0);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render(world, camera.combined);
        batch.begin();
        batch.draw(img, body.getPosition().x - (img.getWidth()/PPM)/2, body.getPosition().y - (img.getHeight()/PPM)/2, img.getWidth()/PPM, img.getHeight()/PPM);
        for(Test test: bodies){
            Vector2 vec = new Vector2();
            vec.setToRandomDirection();

            test.body.applyLinearImpulse(vec, test.body.getPosition(), true);
            //batch.draw(test.img, test.body.getPosition().x, test.body.getPosition().y, img.getWidth()/PPM, img.getHeight()/PPM);
        }
        batch.end();
        camera.update();

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    @Override
    public void resize(int width, int height){
        camera.setToOrtho(false, width/PPM, height/PPM);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                body.applyLinearImpulse(new Vector2(0, 5f), new Vector2(0, 0), true);
                break;
            case Input.Keys.DOWN:
                body.applyLinearImpulse(new Vector2(0, -5f), new Vector2(0, 0), true);
                break;
            case Input.Keys.RIGHT:
                body.applyLinearImpulse(new Vector2(5f, 0),new Vector2(0, 0), true);
                break;
            case Input.Keys.LEFT:
                body.applyLinearImpulse(new Vector2(-5f, 0),new Vector2(0, 0), true);
                break;
            case Input.Keys.A:
                //doesn't have rotation fixture so cannot rotate
                body.applyAngularImpulse(500f, true);
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
