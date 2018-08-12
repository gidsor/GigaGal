package com.gidsor.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.gidsor.gigagal.util.Assets;
import com.gidsor.gigagal.util.Constants;
import com.gidsor.gigagal.util.Utils;

public class ExitPortal {
    public static final String TAG = ExitPortal.class.getName();

    private final Vector2 position;
    private final long startTime;

    public ExitPortal(Vector2 position) {
        this.position = position;
        startTime = TimeUtils.nanoTime();
    }

    public void render(SpriteBatch batch) {
        final float elapsedTime = Utils.secondsSince(startTime);
        final TextureRegion region = (TextureRegion) Assets.instance.exitPortalAssets.exitPortal.getKeyFrame(elapsedTime, true);
        Utils.drawTextureRegion(batch, region, position, Constants.EXIT_PORTAL_CENTER);
    }
}
