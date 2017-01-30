import java.util.Random;
import java.util.Scanner;

/**
 * Created by Денис on 29.01.2017.
 */
public class Utils {

    public static Random rand = new Random();
    public static Scanner sc = new Scanner(System.in);
    public static String answerForAllQuestions = "Извините, данная функция в игре пока не реализована. Ждите дальнейших обновлений";

    //метод для адекватной работы с выбором вариантов
    public static int getAction(int _min, int _max, String _str)
    {
        int x = 0;
        do
        {
            if(_str != "")  System.out.println(_str);
            x = Utils.sc.nextInt();
        } while ( x < _min || x > _max);

        return x;
    }
}
