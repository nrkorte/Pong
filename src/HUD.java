import java.awt.*;

public class HUD {
    public static int score_1, score_2;
    private Handler handler;

    public HUD(Handler handler) {
        this.handler = handler;
    }

    public void tick() {
        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.ball) {
                if (handler.objects.get(i).getX() >= Game.right - 32){
                    score_1++;
                    handler.objects.get(i).setX((((float)Game.right + (float)Game.left) / 2));
                    handler.objects.get(i).setY((((float)Game.top + (float)Game.bottom) / 2));
                }
                else if (handler.objects.get(i).getX() <= Game.left){
                    score_2++;
                    handler.objects.get(i).setX((((float)Game.right + (float)Game.left) / 2));
                    handler.objects.get(i).setY((((float)Game.top + (float)Game.bottom) / 2));
                }
            }
        }
    }
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(Game.left,50, Game.right - Game.left, 25);
        g.drawString("Player 1 Score: " + score_1, 300, 63);
        g.drawString("Player 2 Score: " + score_2, 850, 63);
    }
}
