package com.trando.dungeoncrawler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import javax.swing.*;

import static com.trando.dungeoncrawler.DungeonCrawler.PPM;

/**
 * Created by Cameron on 3/9/2017.
 */
public class Tile {
    public Body body;
    public Sprite img;

    int row;
    int col;

    public Tile(World world, int row, int col){
        this.row = row;
        this.col = col;

        img = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        //reason why can only work in increments of one is right here
        bodyDef.position.set(col, row);
        body = world.createBody(bodyDef);

        //these are in box2d units not pixels so it works perfectly
        // set width to be 16 pixels wide, but then it gets converted to 1 box2d unit, which is 16 pixels wide so it works in box2d units
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / 2/ PPM, 16 /2
                /PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.01f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }
}
