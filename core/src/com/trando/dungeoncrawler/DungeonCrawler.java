package com.trando.dungeoncrawler;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.sun.prism.image.ViewPort;

public class DungeonCrawler extends ApplicationAdapter implements InputProcessor {
    public static final float PPM = 16;
    OrthographicCamera camera;
    World world;
    Vector2 gravity = new Vector2(0, 0);
    SpriteBatch batch;
    Sprite img;

    Body body;

    @Override
    public void create() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        world = new World(gravity, true);
        img = new Sprite(new Texture("badlogic.jpg"));

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(img.getX(), img.getY());
        body = world.createBody(bodyDef);

        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
    }

    int terrible = 0;
    @Override
    public void render() {
        System.out.println(body.getPosition().toString());
        batch.setProjectionMatrix(camera.combined);
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        img.setPosition(body.getPosition().x, body.getPosition().y);
        camera.position.set(body.getPosition().x + 100, body.getPosition().y + 100, 0);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, terrible+=1,0);
        batch.draw(img, img.getX(), img.getY());
        batch.end();
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
                body.applyLinearImpulse(new Vector2(0, 5f), new Vector2(0, 0), true);
                break;
            case Input.Keys.DOWN:
                body.applyLinearImpulse(new Vector2(0, -5f), new Vector2(0, 0), true);
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
