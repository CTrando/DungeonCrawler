package com.trando.dungeoncrawler;

import com.badlogic.gdx.physics.box2d.*;

import static com.trando.dungeoncrawler.DungeonCrawler.PPM;

/**
 * Created by Cameron on 3/21/2017.
 */
public class StaticTile extends Tile {

    public StaticTile(World world, int row, int col) {
        super(world, row, col);
    }

    @Override
    public BodyDef getBodyDef(){
        BodyDef bodyDef = super.getBodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        return bodyDef;
    }

    @Override
    public FixtureDef getFixtureDef(){
        FixtureDef fixtureDef = super.getFixtureDef();
        fixtureDef.filter.categoryBits = GroupIndexes.CATEGORY_OBSTACLE;
        fixtureDef.filter.maskBits = GroupIndexes.MASK_OBSTACLE;
        return fixtureDef;
    }
}
