import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
    /*
    Step 2 is to set up the game constructor. Here we can generate serialVersionUID (option + enter)
    We define the width and height of the player window in this class as static instance variables
    We create an overall handler that we will use to handle the objects
    create the window(in the constructor) as well as any objects that should be there at the start
     */

    private static final long serialVersionUID = 1813904191269004253L;

    public static final int WIDTH = 1300, HEIGHT = WIDTH / 12 * 7;
    //Remember to define a new thread
    private Thread thread;
    private boolean running = false;
    public static int left = 150, top = 100, right = 1150, bottom = 600;
    private HUD hud;

    private Handler handler;
    Random random = new Random();

    public Game () {
        handler = new Handler();
        hud = new HUD(handler);
        this.addKeyListener(new KeyInput(handler));
        //add objects here
        handler.addObject(new Paddle(170, 315, 0, 0, ID.paddle, handler));
        handler.addObject(new Paddle(1110, 315, 0, 0, ID.paddle2, handler));
        int tmp = random.nextInt(3);
        int x = 5;
        switch (tmp) {
            case 0:
                handler.addObject(new Ball((((float)right + (float)left) / 2), (((float)top + (float)bottom) / 2), -x, -x, ID.ball, handler, hud));
                break;
            case 1:
                handler.addObject(new Ball((((float)right + (float)left) / 2), (((float)top + (float)bottom) / 2), x, -x, ID.ball, handler, hud));
                break;
            case 2:
                handler.addObject(new Ball((((float)right + (float)left) / 2), (((float)top + (float)bottom) / 2), -x, x, ID.ball, handler, hud));
                break;
            case 3:
                handler.addObject(new Ball((((float)right + (float)left) / 2), (((float)top + (float)bottom) / 2), x, x, ID.ball, handler, hud));
                break;
        }

        new Window(WIDTH, HEIGHT, "Pong", this);


    }

    /*
    create start and stop, may vary but not yet. Thread is like the interval in JS. Remember the try catch
     */

    public synchronized void start() {
        System.out.println("Initializing...");
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
            System.out.println("Exiting...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        // 9 0s
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
//                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    public void render() {

        //request focus makes it so you dont have to click the window
        this.requestFocus();
        //BufferStrategy makes out FPS to be set at a reasonable rate
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        //create background if needed
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.white);
        g.drawRect(150, 100, 1000, 500);

        //call additional render methods
        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();

    }

    public void tick() {
        handler.tick();
        hud.tick();
    }

    public static void main(String[] args) {
        new Game();
    }

    public static float clamp(float val, float min, float max) {
        if (val >= max) return val = max;
        else if (val <= min) return val = min;
        else return val;
    }
}
