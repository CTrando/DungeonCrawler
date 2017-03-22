package com.trando.dungeoncrawler;

/**
 * Created by Cameron on 3/12/2017.
 */
public class GroupIndexes {
    //if they are of the same group, then they don't collide! Not if they are negative, terrible!
    public static final short CATEGORY_PLAYER = 0x0001;
    public static final short CATEGORY_TILE = 0x0002;
    public static final short CATEGORY_OBSTACLE = 0x0003;

    public static final short MASK_PLAYER = CATEGORY_OBSTACLE;
    public static final short MASK_TILE = 0;
    public static final short MASK_OBSTACLE = CATEGORY_PLAYER;
}
