package com.mygdx.game.levely;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game_items.Bomba;
import game_items.Krabica;
import com.mygdx.game.Reader1;
import java.io.IOException;

/**
 *
 * @author David
 */
public class Level2 extends Level {

    private SpriteBatch batch;

    public Level2(SpriteBatch batch) {
        super(new Texture("level2.jpg"));
        super.pridajKrabicu(new Krabica(2, 2, batch));
        super.pridajKrabicu(new Krabica(2, 3, batch));
        super.pridajKrabicu(new Krabica(3, 3, batch));
        super.pridajBombu(new Bomba(7, 3, batch));
        super.pridajBombu(new Bomba(7, 4, batch));
        super.pridajBombu(new Bomba(7, 5, batch));
        this.pridajPrekazky();
    }

    @Override
    public void pridajPrekazky() {
        try {
            Reader1 r = new Reader1("prekazkyLvl2.txt");
            super.setPolePrekazok(r.getPolePrekazok());
        } catch (IOException ex) {
            System.out.println("nepodarilo sa nacitat subor");
        }
    }

    @Override
    public int getDefaultX() {
        return 3;
    }

    @Override
    public int getDefaultY() {
        return 2;
    }

    @Override
    public void reset() {
        super.vymazArray();
        super.pridajKrabicu(new Krabica(2, 2, this.batch));
        super.pridajKrabicu(new Krabica(2, 3, this.batch));
        super.pridajKrabicu(new Krabica(3, 3, this.batch));
        super.pridajBombu(new Bomba(7, 3, this.batch));
        super.pridajBombu(new Bomba(7, 4, this.batch));
        super.pridajBombu(new Bomba(7, 5, this.batch));
    }

}
