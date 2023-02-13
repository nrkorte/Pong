import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;

        for (int i = 0; i < 4; i++) {
            keyDown[i] = false;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.paddle) {
                if (key == KeyEvent.VK_S) {
                    handler.objects.get(i).setVy(5);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_W) {
                    handler.objects.get(i).setVy(-5);
                    keyDown[1] = true;
                }
            } else if (handler.objects.get(i).getId() == ID.paddle2) {
                if (key == KeyEvent.VK_DOWN) {
                    handler.objects.get(i).setVy(5);
                    keyDown[2] = true;
                }
                if (key == KeyEvent.VK_UP) {
                    handler.objects.get(i).setVy(-5);
                    keyDown[3] = true;
                }
            }
        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(0);
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.paddle) {
                if (key == KeyEvent.VK_S) {
                    keyDown[0] = false;
                }
                if (key == KeyEvent.VK_W) {
                    keyDown[1] = false;
                }
                if (!keyDown[0] && !keyDown[1]) handler.objects.get(i).setVy(0);
            } else if (handler.objects.get(i).getId() == ID.paddle2) {
                if (key == KeyEvent.VK_DOWN) {
                    keyDown[2] = false;
                }
                if (key == KeyEvent.VK_UP) {
                    keyDown[3] = false;
                }
                if (!keyDown[2] && !keyDown[3]) handler.objects.get(i).setVy(0);
            }
        }
    }
}
