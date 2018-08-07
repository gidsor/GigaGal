package com.gidsor.gigagal.util;

import com.badlogic.gdx.graphics.Camera;
import com.gidsor.gigagal.entities.GigaGal;

public class ChaseCam {
    private Camera camera;
    private GigaGal target;

    public ChaseCam(Camera camera, GigaGal target) {
        this.camera = camera;
        this.target = target;
    }

    public void update() {
        camera.position.x = target.position.x;
        camera.position.y = target.position.y;
    }
}
