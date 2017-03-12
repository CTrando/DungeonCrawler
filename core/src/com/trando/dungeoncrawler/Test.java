package com.trando.dungeoncrawler;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import static com.trando.dungeoncrawler.DungeonCrawler.PPM;

/**
 * Created by Cameron on 3/7/2017.
 */
public class Test {
    public Body body;
    Sprite img;


    public Test(World world) {
        img = new Sprite(new Texture("badlogic.jpg"));

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(img.getX(), img.getY());
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(img.getWidth() / 2/ PPM, img.getHeight() /2
                /PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.01f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
    }
}
