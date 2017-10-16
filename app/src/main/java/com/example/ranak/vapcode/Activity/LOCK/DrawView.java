package com.example.ranak.vapcode.Activity.LOCK;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by ranak on 10/10/17.
 */
public class DrawView extends View {
    Paint paint = new Paint();
    int color1=Color.DKGRAY;
    int color2=Color.DKGRAY;
    int color3=Color.DKGRAY;
    int color4=Color.DKGRAY;

    int gridClour = Color.DKGRAY;

    DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
    int w = metrics.widthPixels;
    int h = metrics.heightPixels;
    float coor[]=new float[16];



    public DrawView(Context context,AttributeSet attr) {
        super(context, attr);
    }


    @Override
    public void onDraw(Canvas canvas) {

        /*coor[0] = (float) 0.0625*w;
        coor[1]= (float) 0.1163*h;
        coor[2]= (float) (0.4583*w);
        coor[3]= (float) (0.3256*h);
        coor[4]= (float) (0.5625*w);
        coor[5]= (float) (0.1163*h);
        coor[6]= (float) (0.9375*w);
        coor[7]= (float) (0.3256*h);
        coor[8]= (float) (0.5625*w);
        coor[9]= (float) (0.3837*h);
        coor[10]= (float) (0.9375*w);
        coor[11]= (float) (0.5930*h);
        coor[12]= (float) (0.0625*w);
        coor[13]= (float) (0.3837*h);
        coor[14]= (float) (0.4583*w);
        coor[15]= (float) (0.5930*h);*/

        coor[0] = (float) (0.0095)*w;
        coor[1]= (float) (0.1163)*h;
        coor[2]= (float) (0.4183*w);
        coor[3]= (float) (0.3256*h);

        coor[4]= (float) (0.5112*w);
        coor[5]= (float) (0.1163*h);
        coor[6]= (float) (1.000*w);
        coor[7]= (float) (0.3256*h);

        coor[8]= (float) (0.5112*w);
        coor[9]= (float) (0.3837*h);
        coor[10]= (float) (1.000*w);
        coor[11]= (float) (0.5930*h);

        coor[12]= (float) (0.0095*w);
        coor[13]= (float) (0.3837*h);
        coor[14]= (float) (0.4183*w);
        coor[15]= (float) (0.5930*h);

        paint.setColor(gridClour);
        canvas.drawRect(coor[0], coor[1], coor[2],coor[3], paint );
        paint.setColor(gridClour);
        canvas.drawRect(coor[4], coor[5], coor[6], coor[7], paint );
        paint.setColor(gridClour);
        canvas.drawRect(coor[8], coor[9], coor[10], coor[11], paint );
        paint.setColor(gridClour);
        canvas.drawRect(coor[12], coor[13], coor[14], coor[15], paint);


    }
    public float[] getCoor(){
        return coor;
    }

    public int getPixelsOfScreen(){
        return w;
    }

    public void SetColor()
    {
        color1=Color.LTGRAY;
        color2=Color.BLACK;
        color3=Color.LTGRAY;
        color4=Color.BLACK;
        invalidate();
    }
    public void changecolor1()
    {
        color1=Color.RED;
        invalidate();
    }
    public void changecolor2()
    {
        color2=Color.RED;
        invalidate();
    }
    public void changecolor3()
    {
        color3=Color.RED;
        invalidate();
    }
    public void changecolor4()
    {
        color4=Color.RED;
        invalidate();
    }


}
