package com.gidsor.gigagal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.gidsor.gigagal.util.Assets;
import com.gidsor.gigagal.util.ChaseCam;
import com.gidsor.gigagal.util.Constants;
import com.gidsor.gigagal.util.LevelLoader;

public class GameplayScreen extends ScreenAdapter {
    private static final String TAG = GameplayScreen.class.getName();

    private ChaseCam chaseCam;

    Level level;
    SpriteBatch batch;
    ExtendViewport gameplayViewport;

    @Override
    public void show() {
        AssetManager am = new AssetManager();
        Assets.instance.init(am);

        batch = new SpriteBatch();
        gameplayViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);

//        level = new Level(gameplayViewport);
//        level.initializeDebugLevel();

        level = LevelLoader.load("Level1", gameplayViewport);
        chaseCam = new ChaseCam(gameplayViewport.getCamera(), level.getGigaGal());
    }

    @Override
    public void resize(int width, int height) {
        gameplayViewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        Assets.instance.dispose();
    }

    @Override
    public void render(float delta) {
        level.update(delta);

        chaseCam.update(delta);

        gameplayViewport.apply();

        Gdx.gl.glClearColor(
                Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a
        );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(gameplayViewport.getCamera().combined);
        batch.begin();
        level.render(batch);
        batch.end();
    }
}
