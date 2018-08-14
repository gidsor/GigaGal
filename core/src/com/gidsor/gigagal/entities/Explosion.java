package com.gidsor.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.gidsor.gigagal.util.Assets;
import com.gidsor.gigagal.util.Constants;
import com.gidsor.gigagal.util.Utils;

public class Explosion {
    private final Vector2 position;

    private final long startTime;
    public float offset = 0;

    public Explosion(Vector2 position) {
        this.position = position;
        startTime = TimeUtils.nanoTime();
    }

    public void render(SpriteBatch batch) {
        if (!isFineshed() && !yetToStart()) {
            Utils.drawTextureRegion(
                    batch,
                    (TextureRegion) Assets.instance.explosionAssets.explosion.getKeyFrame(Utils.secondsSince(startTime) - offset),
                    position,
                    Constants.EXPLOSION_CENTER
            );
        }
    }

    public boolean yetToStart() {
        return Utils.secondsSince(startTime) - offset < 0;
    }

    public boolean isFineshed() {
        final float elapsedTime = Utils.secondsSince(startTime) - offset;
        return Assets.instance.explosionAssets.explosion.isAnimationFinished(elapsedTime);
    }
}
