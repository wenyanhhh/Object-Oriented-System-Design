package edu.stanford.cs108.mobiledraw;

/**
 * Created by wenyan on 2/20/18.
 */
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class GRect extends GObject{
    GRect(float x, float y, float height, float width) {
        super(x,y,height,width);
    }

    @Override
    public void drawObject(Canvas canvas, Paint outline, Paint fill) {

        if (getWidth() > 0 && getHeight() > 0) {
            canvas.drawRect(getX(),getY(),getX() + getWidth(), getY() + getHeight(), outline);
            canvas.drawRect(getX(),getY(),getX() + getWidth(), getY() + getHeight(), fill);
        }
        if (getWidth() > 0 && getHeight() < 0) {
            canvas.drawRect(getX(),getY() + getHeight(), getX() + getWidth(), getY(), outline);
            canvas.drawRect(getX(),getY() + getHeight(), getX() + getWidth(), getY(), fill);
        }

        if (getWidth() < 0 && getHeight() > 0) {
            canvas.drawRect(getX() + getWidth(),getY(), getX(), getY() + getHeight(), outline);
            canvas.drawRect(getX() + getWidth(),getY(), getX(), getY() + getHeight(), fill);
        }

        if (getWidth() < 0 && getHeight() < 0) {
            canvas.drawRect(getX() + getWidth(), getY() + getHeight(), getX(), getY(), outline);
            canvas.drawRect(getX() + getWidth(), getY() + getHeight(), getX(), getY(), fill);

        }


    }

}
