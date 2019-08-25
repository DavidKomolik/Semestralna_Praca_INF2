package com.mygdx.game.levely;

import game_items.Bomba;
import game_items.Krabica;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public abstract class Level implements Serializable {

    private transient Texture mapaLevelu;
    private boolean[][] polePrekazok;
    private ArrayList<Krabica> zoznamKrabic;
    private ArrayList<Bomba> zoznamBomb;
    private boolean vyhraty;

    /**
     * Vykreslí daný level
     *
     */
    public Level(Texture mapaLevelu) {
        this.mapaLevelu = mapaLevelu;
        this.polePrekazok = new boolean[9][9];
        this.zoznamKrabic = new ArrayList<Krabica>();
        this.zoznamBomb = new ArrayList<Bomba>();

    }

    public void setMapaLevelu(Texture mapaLevelu) {
        this.mapaLevelu = mapaLevelu;
    }

    public void pridajPrekazku(int surX, int surY) {
        this.polePrekazok[surX][surY] = true;
    }

    public void setPolePrekazok(boolean[][] polePrekazok) {
        this.polePrekazok = polePrekazok;
    }

    public void pridajKrabicu(Krabica k) {
        this.zoznamKrabic.add(k);
    }

    public void pridajBombu(Bomba b) {
        this.zoznamBomb.add(b);
    }

    public abstract void reset();

    public abstract int getDefaultX();

    public abstract int getDefaultY();

    public void vykresliKrabice() {
        for (Bomba bomba : this.zoznamBomb) {
            bomba.vykresliSa();
        }
        for (Krabica krabica : this.zoznamKrabic) {
            krabica.vykresliSa();
        }

    }

    public void vykresliLevel(SpriteBatch batch) {
        this.vykresliTexturu(this.mapaLevelu, 0, 0, batch);
        this.vykresliKrabice();
        if (this.overVyhru()) {
            System.out.println("vyhral si");
            this.vyhraty = true;
        }
    }

    public boolean siVyhraty() {
        return this.vyhraty;
    }

    public boolean jeTamStena(int surX, int surY) {
        if (surX > 8 || surY > 8 || surX < 1 || surY < 1) { //pre istotu
            return true;
        } else {
            return this.polePrekazok[surX][surY];
        }
    }

    public Krabica jeTamKrabica(int surX, int surY) {
        for (Krabica krabica : this.zoznamKrabic) {
            if (krabica.getSurX() == surX && krabica.getSurY() == surY) {
                return krabica;
            }
        }
        return null;
    }

    public abstract void pridajPrekazky();

    public boolean overVyhru() {
        Texture tick = new Texture("krabicaTick.jpg");
        Texture normal = new Texture("krabica.jpg");
        int pocetZhod = 0;
        for (Krabica krabica : this.zoznamKrabic) {
            for (Bomba bomba : this.zoznamBomb) {
                if (krabica.getSurX() == bomba.getSurX() && krabica.getSurY() == bomba.getSurY()) {
                    krabica.setTextura(tick);
                    pocetZhod++;
                    break;
                } else {
                    krabica.setTextura(normal);
                }
            }
        }
        return pocetZhod == this.zoznamKrabic.size();
    }

    public void vykresliTexturu(Texture textura, int surX, int surY, SpriteBatch batch) {
        int y = Math.abs(surY * textura.getHeight() - Gdx.graphics.getHeight()) - textura.getHeight();
        batch.draw(textura, surX * textura.getWidth(), y);
    }

    public void vymazArray() {
        this.zoznamBomb.clear();
        this.zoznamKrabic.clear();
    }

}
