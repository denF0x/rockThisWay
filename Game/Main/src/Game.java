import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Денис on 26.01.2017.
 */
public class Game extends Canvas implements Runnable{
//переменные
    private static final long serialVersionUID = 1L;
    public static int WIDTH = 1920;
    public static int HEIGHT = 1080;
    public static String NAME = "Iking";
    public static Unicorn hero;
    public boolean running;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private static int x = 1500;
    private static int y = 600;



//методы
    public Unicorn getUnicorn(String path) {
        BufferedImage sourceImage = null;

        try {
            URL url = this.getClass().getClassLoader().getResource(path);
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Unicorn unicorn = new Unicorn(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()));

        return unicorn;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JFrame frame = new JFrame(Game.NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //выход - ESC
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER); //добавление холста???
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        game.start();
    }


    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        long delta;

        init();

        while(running) {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            update(delta);
            render();
        }
    }

    public void init() {
        hero = getUnicorn("Unicorn.png");
        addKeyListener(new KeyInputHandler());
    }

    public void update(long delta) {
        if (leftPressed == true){
            x--;
        }
        if (rightPressed == true){
            x++;
        }
        if (upPressed == true){
            y--;
        }
        if (downPressed == true){
            y++;
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(2); //есть подозрение, что в документации это зовется DoubleBuffering
            requestFocus();
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.pink);
        g.fillRect(0,0,getWidth(),getHeight());
        hero.draw(g, x, y);
        g.dispose();
        bs.show();
    }


    public void start(){
        running = true;
        new Thread(this).start();
    }


    private class KeyInputHandler extends KeyAdapter {
        public void keyPressed (KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT){
                leftPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                rightPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP){
                upPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN){
                downPressed = true;
            }

        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                rightPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP){
                upPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN){
                downPressed = false;
            }
        }
    }
}
