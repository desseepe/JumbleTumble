package capsicum.game.JumbleTumble;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    public static Canvas canvas;
    private int targetFPS = 60;
    private double averageFPS;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView){

        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

    } // constructor

    public void setRunning (boolean isRunning){
        running = isRunning;
    } // setRunning


    @Override
    public void run(){
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / targetFPS;

        while(running){
            startTime = System.nanoTime();
            canvas = null;

            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } // try
            catch (Exception e) {
            } // catch
            finally {
                if(canvas != null){
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } // try
                    catch (Exception e){
                        e.printStackTrace();
                    } // catch
                } // if
            } // finally

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try{
                this.sleep(waitTime);
            } // try
            catch (Exception e) {
            } // catch

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if(frameCount == targetFPS){
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                totalTime = 0;
                frameCount = 0;
                System.out.println(averageFPS);
            } // if


        } // while

    } // run



}
