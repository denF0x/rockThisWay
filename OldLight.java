/**
 * Created by Денис on 26.09.2016.
 */
public class OldLight extends Light {

    @Override
    public void brighten() {
        System.out.println("Не могу ярче, я не регулируюсь");
    }

    @Override
    public void dim() {
        System.out.println("Не могу темнее, я не регулируюсь");
    }
}
