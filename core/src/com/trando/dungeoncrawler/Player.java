package com.trando.dungeoncrawler;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.trando.dungeoncrawler.components.*;

import static com.trando.dungeoncrawler.DungeonCrawler.PPM;

/**
 * Created by Cameron on 3/11/2017.
 */
public class Player extends Entity {

    public Player(World world){
        BodyComponent bc = new BodyComponent(world);
        RenderComponent rc = new RenderComponent(new Sprite(new Texture(Gdx.files.internal("player.png"))));
        PlayerControlledComponent pc = new PlayerControlledComponent();
        CameraFocusComponent cfc = new CameraFocusComponent();
        AnimationComponent ac = new AnimationComponent();
        StateComponent sc = new StateComponent();

        this.add(bc);
        this.add(rc);
        this.add(pc);
        this.add(cfc);
        this.add(ac);
        this.add(sc);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        //reason why can only work in increments of one is right here
        bodyDef.position.set(1, 1);
        bc.setBody(world.createBody(bodyDef));

        //these are in box2d units not pixels so it works perfectly
        // set width to be 16 pixels wide, but then it gets converted to 1 box2d unit, which is 16 pixels wide so it works in box2d units
        // will render because of how box2d debug renderer works
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PPM / 2/ PPM, PPM /2
                /PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.01f;
       // fixtureDef.restitution = 0.5f;
        fixtureDef.filter.groupIndex = GroupIndexes.GROUP_PLAYER;

        bc.getBody().createFixture(fixtureDef);
        shape.dispose();
    }
}
