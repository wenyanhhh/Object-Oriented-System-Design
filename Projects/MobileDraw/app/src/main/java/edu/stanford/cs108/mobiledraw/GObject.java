package edu.stanford.cs108.mobiledraw;

/**
 * Created by wenyan on 2/20/18.
 */

import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.RectF;

abstract class GObject {
    private float x;
    private float y;
    private float height;
    private float width;

    GObject(float x, float y, float height, float width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y =  y;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    abstract public void drawObject(Canvas canvas, Paint outline, Paint fill);



}
