import java.util.Scanner;

/**
 * Created by Денис on 26.09.2016.
 */
public class LightsOwner {
    public static void main(String[] args) {
        NewLight newLight = new NewLight();
        OldLight oldLight = new OldLight();
        for (int i=0;i<10000;i++) {
            Scanner scanner = new Scanner(System.in);
            String string = scanner.nextLine();
            if (string.equals("Новая лампочка, включить") || string.equals("Новая лампочка, включись")) {
                newLight.on();
            } else if (string.equals("Новая лампочка, выключись") ||  string.equals("Новая лампочка, выключить")) {
                newLight.off();
            } else if (string.equals("Новая лампочка, ярче")) {
                newLight.brighten();
            } else if (string.equals("Новая лампочка, темнее")) {
                newLight.dim();
            } else if (string.equals("Старая лампочка, включить") || string.equals("Старая лампочка, включись")) {
                oldLight.on();
            } else if (string.equals("Старая лампочка, выключись") || string.equals("Старая лампочка, выключить")) {
                oldLight.off();
            } else if (string.equals("Старая лампочка, ярче")) {
                oldLight.brighten();
            } else if (string.equals("Старая лампочка, темнее")) {
                oldLight.dim();
            } else{
                System.out.println("Нет такой буквы в этом слове. Попробуй еще!");
            }
        }
    }
}
