package capsicum.game.JumbleTumble;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Box {
    private int x1 = 100, y1 = 100, x2 = 200, y2 = 200;
    private int xVelocity = 10, yVelocity = 5;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private Paint paint = new Paint();

    public Box(){
        paint.setColor(Color.rgb(250,0,0));

    } // Box constructor

    public void draw(Canvas canvas){
        canvas.drawRect(x1, y1, x2, y2, paint);
    }

    public void update(){

//        if(x1 < 0 && y1 < 0){
//            x1 = screenWidth/2;
//            y1 = screenHeight/2;
//        } // outer if
//        else {
            y1 += yVelocity;
            y2 += yVelocity;

            if((y1 > screenHeight - 100) || (y1<0)){
                yVelocity = yVelocity*-1;
            } // inner if 2

//        } // else

    } // update






} // end of class Box
