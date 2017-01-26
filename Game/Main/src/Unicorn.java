import java.awt.*;

/**
 * Created by Денис on 26.01.2017.
 */
public class Unicorn {
        private Image image;

        public Unicorn(Image image) {
            this.image = image;
        }

        public int getWidth(){
            return image.getWidth(null);
        }

        public int getHeight() {
            return image.getHeight(null);
        }

        public void draw(Graphics g, int x, int y){
            g.drawImage(image,x,y,null);
        }
}

