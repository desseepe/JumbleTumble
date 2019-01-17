package capsicum.game.JumbleTumble;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Character character;
    private Box box;
    private Paint paint = new Paint();
    private int x1 = 100, y1 = 100, x2 = 200, y2 = 200;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        character = new Character(BitmapFactory.decodeResource(getResources(), R.drawable.char1));
        box = new Box();

        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;

        while(retry){

            try{
                thread.setRunning(false);
                thread.join();
            } // try
            catch (InterruptedException e){
                e.printStackTrace();
            } // catch

            retry = false;
        } // while

    } // surfaceDestroyed

    public void update(){
//        character.update();
        box.update();

    }

    @Override
    public void draw (Canvas canvas){
        super.draw(canvas);

        if(canvas != null){
//            character.draw(canvas);

            canvas.drawColor(Color.WHITE);
            box.draw(canvas);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) { // method to get user input
        return super.onTouchEvent(event);
    }
}
