/**
 * Created by Денис on 26.09.2016.
 */
public class NewLight extends Light {
    @Override
    public void brighten() {
        System.out.println("Легко, светим поярче");
    }

    @Override
    public void dim() {
        System.out.println("Без проблем, свечу темнее");
    }
}
