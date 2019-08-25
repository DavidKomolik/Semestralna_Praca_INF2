

package game_items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.levely.Level;

/**
 *
 * @author David
 */
public class Bomba extends Predmet {

    public Bomba(int surX, int surY, SpriteBatch batch) {
        super(new Texture("bomba.jpg"), surX, surY, batch);
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
