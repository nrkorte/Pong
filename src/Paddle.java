import java.awt.*;

public class Paddle extends GameObject{

    private float width;
    private float height;

    public Paddle (float x, float y, float vx, float vy, ID id, Handler handler) {
        super(x, y, id, handler);
        this.vx= vx;
        this.vy = vy;
    }

    @Override
    public void tick() {
        y += vy;

        y = Game.clamp(y, 100, 531);

        collision();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 20, 70);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 20, 70);
    }

    public void collision() {
        this.width = (float) getBounds().width;
        this.height = (float) getBounds().height;
        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.ball) {
                GameObject current = handler.objects.get(i);
                if (getBounds().intersects(current.getBounds())) {
                    if (this.getId() == ID.paddle) {
                        if (current.getBounds().y + current.getBounds().height < this.getX() || current.getBounds().y > this.getX() + height) {
                            current.setVx(current.getVx() * -1);
                            current.setX(this.getX() + width + 3);
                        } else {
                            current.setVy(current.getVy() * -1);
                        }
                    }
                    else if (this.getId() == ID.paddle2) {
                        //if the bottom of the ball is less than the top of the paddle and the top if the ball is greater than the bottom of the paddle
                        if (current.getBounds().y + current.getBounds().height < this.getX() || current.getBounds().y > this.getX() + height) {
                            current.setVx(current.getVx() * -1);
                            current.setX(this.getX() - current.getBounds().width - 3);
                        } else {
                            current.setVy(current.getVy() * -1);
                        }
                    }
                }

            }
        }
    }
}
//                if ((this.getId() == ID.paddle && current.getX() < this.getX() + this.width) || (this.getId() == ID.paddle2 && current.getX() + current.getBounds().width > this.getX())) {
//                    if (current.getBounds().intersectsLine(x, y, x + this.getBounds().width, y) || current.getBounds().intersectsLine(x, y + (int) height, x + (int) width, y + (int) height))
//                        current.setVy(current.getVy() * -1);
//                }
