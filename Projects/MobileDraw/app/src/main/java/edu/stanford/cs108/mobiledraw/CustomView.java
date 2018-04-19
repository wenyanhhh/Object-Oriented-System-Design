package edu.stanford.cs108.mobiledraw;

/**
 * Created by wenyan on 2/20/18.
 */

import android.graphics.Canvas;
import android.provider.MediaStore;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Color;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.graphics.RectF;


import java.util.ArrayList;
import java.util.List;

public class CustomView extends View {
    private GObject rectangle;
    private GObject oval;
    private GObject selectedObject; //used to store current selected object
    private List<GObject> objectList = new ArrayList<>();

    Paint redOutlinePaint;
    Paint blueOutlinePaint;
    Paint whiteFilledPaint;


    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        redOutlinePaint = new Paint();
        redOutlinePaint.setColor(Color.RED);
        redOutlinePaint.setStyle(Paint.Style.STROKE);
        redOutlinePaint.setStrokeWidth(5.0f);

        blueOutlinePaint = new Paint();
        blueOutlinePaint.setColor(Color.BLUE);
        blueOutlinePaint.setStyle(Paint.Style.STROKE);
        blueOutlinePaint.setStrokeWidth(15.0f);

        whiteFilledPaint = new Paint();
        whiteFilledPaint.setColor(Color.WHITE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // from the earliest one to the latest one
        for (int i = 0; i < objectList.size(); i++) {
            GObject object = objectList.get(i);
            object.drawObject(canvas,redOutlinePaint, whiteFilledPaint);
            if (object == selectedObject) {
                object.drawObject(canvas,blueOutlinePaint, whiteFilledPaint);
            }
            displayData();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        RadioGroup modes = (RadioGroup)((MainActivity) getContext()).findViewById(R.id.modes_group);
        int currentCheck = modes.getCheckedRadioButtonId();
        switch(currentCheck) {

            case R.id.select:
                selectObject(event);
                break;
            case R.id.rect:
                drawRectangle(event);
                break;
            case R.id.oval:
                drawOval(event);
                break;
            case R.id.erase:
                eraseObject(event);

        }
        return true;

    }


    public void drawRectangle(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                rectangle = new GRect(x, y, 0, 0);
                selectedObject = rectangle;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                rectangle.setWidth(x - rectangle.getX());
                rectangle.setHeight(y - rectangle.getY());
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                objectList.add(rectangle);
                invalidate();
        }

    }

    public void drawOval(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                oval = new GOval(x, y, 0, 0);
                selectedObject = oval;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                oval.setWidth(x - oval.getX());
                oval.setHeight(y - oval.getY());
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                objectList.add(oval);
                invalidate();
        }

    }


    public void selectObject(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        selectedObject = null;
        int action = event.getAction();

        for (int i = 0; i < objectList.size(); i++) {

            GObject currentObject = objectList.get(i);

            boolean a = x >= currentObject.getX() && x<= currentObject.getX() + currentObject.getWidth()
                    && y >= currentObject.getY() && y<= currentObject.getY() + currentObject.getHeight();
            boolean b = x >= currentObject.getX() && x<= currentObject.getX() + currentObject.getWidth()
                    && y <= currentObject.getY() && y>= currentObject.getY() + currentObject.getHeight();
            boolean c = x >= currentObject.getX() + currentObject.getWidth() && x <= currentObject.getX()
                    && y >= currentObject.getY() && y<= currentObject.getY() + currentObject.getHeight();
            boolean d =  x<= currentObject.getX() && x>= currentObject.getX() + currentObject.getWidth()
                    && y <= currentObject.getY() && y>= currentObject.getY() + currentObject.getHeight();

            if(a||b||c||d) {
                selectedObject = currentObject;
            }

        }
        invalidate();


    }

    public void eraseObject(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        selectedObject = null;


        for (int i = 0; i < objectList.size(); i++) {

            GObject currentObject = objectList.get(i);

            boolean a = x >= currentObject.getX() && x<= currentObject.getX() + currentObject.getWidth()
                    && y >= currentObject.getY() && y<= currentObject.getY() + currentObject.getHeight();
            boolean b = x >= currentObject.getX() && x<= currentObject.getX() + currentObject.getWidth()
                    && y <= currentObject.getY() && y>= currentObject.getY() + currentObject.getHeight();
            boolean c = x >= currentObject.getX() + currentObject.getWidth() && x <= currentObject.getX()
                    && y >= currentObject.getY() && y<= currentObject.getY() + currentObject.getHeight();
            boolean d =  x<= currentObject.getX() && x>= currentObject.getX() + currentObject.getWidth()
                    && y <= currentObject.getY() && y>= currentObject.getY() + currentObject.getHeight();

            if (a || b || c || d) {
                selectedObject = currentObject;
            }

        }

        int action = event.getAction();
        switch(action) {
            case MotionEvent.ACTION_UP:
                objectList.remove(selectedObject);
                selectedObject = null;
                invalidate();

        }

    }

    GObject newObject;

    public void updateObject() {
        EditText xText = (EditText)((MainActivity) getContext()).findViewById(R.id.x_input);
        EditText yText = (EditText)((MainActivity) getContext()).findViewById(R.id.y_input);
        EditText heightText = (EditText)((MainActivity) getContext()).findViewById(R.id.height_input);
        EditText widthText = (EditText)((MainActivity) getContext()).findViewById(R.id.width_input);


        float x = Float.parseFloat(xText.getText().toString());
        float y = Float.parseFloat(yText.getText().toString());
        float height = Float.parseFloat(heightText.getText().toString());
        float width = Float.parseFloat(widthText.getText().toString());

        if (selectedObject != null) {
            selectedObject.setX(x);
            selectedObject.setY(y);
            selectedObject.setWidth(width);
            selectedObject.setHeight(height);
            invalidate();

        } else {

            RadioGroup modes = (RadioGroup)((MainActivity) getContext()).findViewById(R.id.modes_group);
            int currentCheck = modes.getCheckedRadioButtonId();
            switch(currentCheck) {
                case R.id.rect:
                    newObject = new GRect(x, y, height, width);
                    objectList.add(newObject);
                    selectedObject = newObject;
                    invalidate();
                    break;
                case R.id.oval:
                    newObject = new GOval(x, y, height, width);
                    objectList.add(newObject);
                    selectedObject = newObject;
                    invalidate();
                default: break;
            }


        }

    }

    private void displayData() {
        EditText xText = (EditText) ((MainActivity) getContext()).findViewById(R.id.x_input);
        EditText yText = (EditText) ((MainActivity) getContext()).findViewById(R.id.y_input);
        EditText widthText = (EditText) ((MainActivity) getContext()).findViewById(R.id.width_input);
        EditText heightText = (EditText) ((MainActivity) getContext()).findViewById(R.id.height_input);

        if (selectedObject== null) {
            xText.setText("");
            yText.setText("");
            widthText.setText("");
            heightText.setText("");

        } else {
            String xString = "";
            String yString = "";
            String widthString = "";
            String heightString = "";

            if (selectedObject.getWidth() > 0 && selectedObject.getHeight() > 0) {
                xString = (float) (Math.round(selectedObject.getX() * 10000)) / 10000 + "";
                yString = (float) (Math.round(selectedObject.getY() * 10000)) / 10000 + "";
                widthString = Math.abs((float) (Math.round(selectedObject.getWidth() * 10000)) / 10000) + "";
                heightString = Math.abs((float) (Math.round(selectedObject.getHeight() * 10000)) / 10000) + "";

            } else if (selectedObject.getWidth() > 0 && selectedObject.getHeight() < 0) {
                xString = (float)(Math.round(selectedObject.getX()*10000))/10000 + "";
                yString = (float)(Math.round((selectedObject.getY() + selectedObject.getHeight())*10000))/10000 + "";
                widthString = Math.abs((float)(Math.round(selectedObject.getWidth()*10000))/10000)+ "";
                heightString = Math.abs((float)(Math.round(selectedObject.getHeight()*10000))/10000) + "";
            } else if (selectedObject.getWidth() < 0 && selectedObject.getHeight() > 0) {
                xString = (float)(Math.round((selectedObject.getX() + selectedObject.getWidth())*10000))/10000 + "";
                yString = (float)(Math.round((selectedObject.getY() + selectedObject.getHeight())*10000))/10000 + "";
                widthString = Math.abs((float)(Math.round(selectedObject.getWidth()*10000))/10000)+ "";
                heightString = Math.abs((float)(Math.round(selectedObject.getHeight()*10000))/10000) + "";
            } else if (selectedObject.getWidth() < 0 && selectedObject.getHeight() < 0) {
                xString = (float)(Math.round((selectedObject.getX() + selectedObject.getWidth())*10000))/10000 + "";
                yString = (float)(Math.round((selectedObject.getY() + selectedObject.getHeight())*10000))/10000 + "";
                widthString = Math.abs((float)(Math.round(selectedObject.getWidth()*10000))/10000)+ "";
                heightString = Math.abs((float)(Math.round(selectedObject.getHeight()*10000))/10000) + "";

            }


            xText.setText(xString);
            yText.setText(yString);
            widthText.setText(widthString);
            heightText.setText(heightString);

        }

    }

}
