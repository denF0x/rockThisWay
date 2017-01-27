/**
 * Created by Денис on 27.01.2017.
 */
public class Monster extends GameCharacter {
    public Monster(String _charClass, String _name, int _hp, int _attack, int _defense) {
        super(_charClass, _name, _hp, _attack, _defense);
    }
    public Object clone(){
        try{
            return super.clone();
        } catch (CloneNotSupportedException e) {
           //System.out.println("Клонирование невозможно");
            return this;
        }
    }
}
