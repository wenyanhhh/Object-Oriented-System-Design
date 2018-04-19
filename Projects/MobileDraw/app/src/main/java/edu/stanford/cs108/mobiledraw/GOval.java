package edu.stanford.cs108.mobiledraw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by wenyan on 2/20/18.
 */

public class GOval extends GObject {
    private RectF rectangleForDrawing;

    GOval(float x, float y, float height, float width) {
        super(x,y,height,width);
    }

    @Override
    public void drawObject(Canvas canvas, Paint outline, Paint fill) {

        if (getWidth() > 0 && getHeight() > 0) {
            rectangleForDrawing = new RectF(getX(),getY(),getX() + getWidth(), getY() + getHeight());
            canvas.drawOval(rectangleForDrawing, outline);
            canvas.drawOval(rectangleForDrawing, fill);
        }
        if (getWidth() > 0 && getHeight() < 0) {
            rectangleForDrawing = new RectF(getX(),getY() + getHeight(), getX() + getWidth(), getY());
            canvas.drawOval(rectangleForDrawing, outline);
            canvas.drawOval(rectangleForDrawing, fill);
        }

        if (getWidth() < 0 && getHeight() > 0) {
            rectangleForDrawing = new RectF(getX() + getWidth(),getY(), getX(), getY() + getHeight());
            canvas.drawOval(rectangleForDrawing, outline);
            canvas.drawOval(rectangleForDrawing, fill);
        }

        if (getWidth() < 0 && getHeight() < 0) {
            rectangleForDrawing = new RectF(getX() + getWidth(), getY() + getHeight(), getX(), getY());
            canvas.drawOval(rectangleForDrawing, outline);
            canvas.drawOval(rectangleForDrawing, fill);
        }


    }
}
