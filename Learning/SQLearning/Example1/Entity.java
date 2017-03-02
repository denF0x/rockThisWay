package Learning.SQLearning.Example1;

import java.io.Serializable;

/**
 * Created by Денис on 25.02.2017.
 */
public abstract class Entity implements Serializable, Cloneable {
    protected int id;
    public Entity() {
    }
    public Entity(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
