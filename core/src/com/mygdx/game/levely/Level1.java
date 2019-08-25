package com.mygdx.game.levely;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import game_items.Bomba;
import game_items.Krabica;
import com.mygdx.game.Reader1;
import java.io.IOException;
import java.util.logging.Logger;

public class Level1 extends Level {
    private SpriteBatch batch;
    public Level1(SpriteBatch batch) {
        super(new Texture("level1.jpg"));
        this.batch = batch;
        super.pridajKrabicu(new Krabica(3, 4, batch));
        super.pridajKrabicu(new Krabica(3, 3, batch));
        super.pridajKrabicu(new Krabica(4, 5, batch));
        super.pridajKrabicu(new Krabica(5, 3, batch));
        super.pridajBombu(new Bomba(3, 1, batch));
        super.pridajBombu(new Bomba(1, 4, batch));
        super.pridajBombu(new Bomba(4, 6, batch));
        super.pridajBombu(new Bomba(6, 3, batch));
        this.pridajPrekazky();

    }


    @Override
    public void pridajPrekazky() {
        try {
            Reader1 r = new Reader1("prekazkyLvl1.txt");
            super.setPolePrekazok(r.getPolePrekazok());
        } catch (IOException ex) {
            Logger.getLogger(Level1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getDefaultX() {
        return 4;
    }

    @Override
    public int getDefaultY() {
        return 4;
    }

    @Override
    public void reset() {
        super.vymazArray();
        super.pridajKrabicu(new Krabica(3, 4, this.batch));
        super.pridajKrabicu(new Krabica(3, 3, this.batch));
        super.pridajKrabicu(new Krabica(4, 5, this.batch));
        super.pridajKrabicu(new Krabica(5, 3, this.batch));
        super.pridajBombu(new Bomba(3, 1, this.batch));
        super.pridajBombu(new Bomba(1, 4, this.batch));
        super.pridajBombu(new Bomba(4, 6, this.batch));
        super.pridajBombu(new Bomba(6, 3, this.batch));
        
    }


}
