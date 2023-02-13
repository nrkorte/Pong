import java.awt.*;

public class Ball extends GameObject {
    private HUD hud;

    public Ball(float x, float y, float vx, float vy, ID id, Handler handler, HUD hud) {
        super(x, y, id, handler);
        this.vx = vx;
        this.vy = vy;
        this.hud = hud;
    }

    @Override
    public void tick() {
        int tmp = 32;

        y += vy;
        x += vx;

        if (x >= Game.right - tmp || x <= Game.left) vx *= -1;
        if (y >= Game.bottom - tmp || y <= Game.top) vy *= -1;

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillOval((int)x, (int)y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
