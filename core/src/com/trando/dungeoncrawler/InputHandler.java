package com.trando.dungeoncrawler;

import com.badlogic.gdx.InputAdapter;

import java.util.HashMap;

/**
 * Created by Cameron on 3/11/2017.
 */
public class InputHandler extends InputAdapter {
    private HashMap<Integer, Boolean> keys = new HashMap<Integer, Boolean>();

    @Override
    public boolean keyDown(int keycode) {
        keys.put(keycode, true);
        return true;
    }

    @Override
    public boolean keyUp(int keycode){
        keys.put(keycode, false);
        return true;
    }

    public boolean isKeyPressed(int keycode){
        if(keys.get(keycode) == null) return false;
        return keys.get(keycode);
    }
}
