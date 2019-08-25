package game_items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.levely.Level;
import java.util.Date;

/**
 *
 * @author David
 */
public class Krabica extends Predmet {

    public Krabica(int surX, int surY, SpriteBatch batch) {
        super(new Texture("krabica.jpg"), surX, surY, batch);

    }

    public void blikaj() {

        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;
        while (elapsedTime < 100) {

            super.setTextura(new Texture("biela.jpg"));
            super.vykresliSa(new Texture("biela.jpg"));
            elapsedTime = (new Date()).getTime() - startTime;
        }

    }

    public boolean posunDole(Level aktualnyLevel) {
        if (aktualnyLevel.jeTamKrabica(super.getSurX(), super.getSurY() + 1) != null) {

            aktualnyLevel.jeTamKrabica(super.getSurX(), super.getSurY() + 1).blikaj();
            return false;
        }
        return super.posunDole(aktualnyLevel); 
    }

    @Override
    public boolean posunHore(Level aktualnyLevel) {
        if (aktualnyLevel.jeTamKrabica(super.getSurX(), super.getSurY() - 1) != null) {

            aktualnyLevel.jeTamKrabica(super.getSurX(), super.getSurY() + -1).blikaj();

            return false;
        }
        return super.posunHore(aktualnyLevel);
    }

    @Override
    public boolean posunVlavo(Level aktualnyLevel) {
        if (aktualnyLevel.jeTamKrabica(super.getSurX() - 1, super.getSurY()) != null) {
            return false;
        }
        return super.posunVlavo(aktualnyLevel); 
    }

    @Override
    public boolean posunVpravo(Level aktualnyLevel) {
        if (aktualnyLevel.jeTamKrabica(super.getSurX() + 1, super.getSurY()) != null) {
            return false;
        }
        return super.posunVpravo(aktualnyLevel); 
    }

}
