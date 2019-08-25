package game_items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.levely.Level;
import java.io.Serializable;

/**
 *
 * @author David
 */
public class Tehla extends Predmet implements Serializable {

    public Tehla(int surX, int surY, SpriteBatch batch) {
        super(new Texture("tehla.jpg"), surX, surY, batch);
    }

    @Override
    public boolean posunDole(Level aktualnyLevel) {
        return false;
    }

    @Override
    public boolean posunHore(Level aktualnyLevel) {
        return false;
    }

    @Override
    public boolean posunVlavo(Level aktualnyLevel) {
        return false;
    }

    @Override
    public boolean posunVpravo(Level aktualnyLevel) {
        return false;
    }

}
