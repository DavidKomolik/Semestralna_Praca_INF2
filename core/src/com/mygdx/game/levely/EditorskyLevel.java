package com.mygdx.game.levely;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game_items.Bomba;
import game_items.Krabica;
import game_items.Predmet;
import game_items.Tehla;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * INPUT : SPACE - nova prekazka (tehla) 
 *         ENTER - nova krabica
 *         END - nova bomba
 *         HOME - ulozenie levelu
 *         Sipky - ovladanie kurzoru
 * 
 * @author David
 */
public class EditorskyLevel extends Level implements Serializable {

    private int defaultSurX;
    private int defaultSurY;
    private transient SpriteBatch batch;
    private ArrayList<Predmet> zoznamPredmetov;
    private ArrayList<Tehla> zoznamTehal;
    private boolean siEditovany;

    public EditorskyLevel(Texture mapaLevelu) {
        super(mapaLevelu);
        this.siEditovany = true;
        this.zoznamTehal = new ArrayList<Tehla>();
        this.zoznamPredmetov = new ArrayList<Predmet>();
        this.defaultSurX = 5;
        this.defaultSurY = 5;
    }

    public void setSiEditovany(boolean siEditovany) {
        this.siEditovany = siEditovany;
    }

    @Override
    public boolean overVyhru() {
        if (this.siEditovany) {
            return false;
        } else {
            return super.overVyhru();
        }

    }

    @Override
    public void vykresliLevel(SpriteBatch batch) {
        super.vykresliLevel(batch);
        for (Tehla tehla : this.zoznamTehal) {
            tehla.vykresliSa();
        }
        this.batch = batch;

    }

    @Override
    public Krabica jeTamKrabica(int surX, int surY) {
        if (this.siEditovany) {
            return null;
        } else {
            return super.jeTamKrabica(surX, surY);
        }

    }

    @Override
    public boolean jeTamStena(int surX, int surY) {
        if (this.siEditovany) {
            return surX > 8 || surY > 8 || surX < 0 || surY < 0;
        } else {
            return super.jeTamStena(surX, surY);
        }

    }

    @Override
    public boolean siVyhraty() {
        if (this.siEditovany) {
            return false;
        } else {
            return super.siVyhraty();
        }

    }

    @Override
    public void pridajBombu(Bomba b) {
        super.pridajBombu(b); //To change body of generated methods, choose Tools | Templates.
        this.zoznamPredmetov.add(b);
    }

    @Override
    public void pridajKrabicu(Krabica k) {
        super.pridajKrabicu(k); //To change body of generated methods, choose Tools | Templates.
        this.zoznamPredmetov.add(k);
    }

    @Override
    public void pridajPrekazku(int surX, int surY) {
        super.pridajPrekazku(surX, surY);
        Tehla t = new Tehla(surX, surY, this.batch);
//        super.pridajKrabicu(k);
        this.zoznamTehal.add(t);
        this.zoznamPredmetov.add(t);
    }

    public void priradTexturyNaspat(SpriteBatch batch) {
        for (Predmet predmet : this.zoznamPredmetov) {
            if (predmet instanceof Krabica) {
                predmet.setTextura(new Texture("krabica.jpg"));
            }

            if (predmet instanceof Bomba) {
                predmet.setTextura(new Texture("bomba.jpg"));
            }

            if (predmet instanceof Tehla) {
                predmet.setTextura(new Texture("tehla.jpg"));
            }
            predmet.setBatch(batch);
        }
    }

    @Override
    public int getDefaultX() {
        return this.defaultSurX;
    }

    @Override
    public int getDefaultY() {
        return this.defaultSurY;
    }

    @Override
    public void pridajPrekazky() {

    }

    @Override
    public void reset() {

    }

    public void ulozLevel() throws FileNotFoundException, IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("vlastnyLevel.ser")));
        oos.writeObject(this);
        oos.close();
        System.out.println("ok");
    }

}
