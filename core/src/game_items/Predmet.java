package game_items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.levely.Level;
import java.io.Serializable;

/**
 *
 * @author David
 */
public abstract class Predmet implements Serializable {

    private int surX;
    private int surY;
    private transient Texture textura;
    private transient SpriteBatch batch;

    public Predmet(Texture textura, int surX, int surY, SpriteBatch batch) {
        this.textura = textura;
        this.surX = surX;
        this.surY = surY;
        this.batch = batch;
    }
    
    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public void vykresliSa() {
        this.vykresliTexturu(this.textura, this.surX, this.surY, this.batch);
    }
    
    public void vykresliSa(Texture textura) {
        this.vykresliTexturu(textura, this.surX, this.surY, this.batch);
    }

    public Texture getTextura() {
        return this.textura;
    }

    public int getSurX() {
        return this.surX;
    }

    public int getSurY() {
        return this.surY;
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }
    
    

    public void vykresliTexturu(Texture textura, int surX, int surY, SpriteBatch batch) {
        int y = Math.abs(surY * textura.getHeight() - Gdx.graphics.getHeight()) - textura.getHeight();
        batch.draw(textura, surX * textura.getWidth(), y);
    }

    public boolean posunVpravo(Level aktualnyLevel) {
        if (!aktualnyLevel.jeTamStena(this.surX + 1, this.surY)) {
            this.surX++;
            return true;
        }
        return false;
    }

    public boolean posunVlavo(Level aktualnyLevel) {
        if (!aktualnyLevel.jeTamStena(this.surX - 1, this.surY)) {
            this.surX--;
            return true;
        }
        return false;
    }

    public boolean posunHore(Level aktualnyLevel) {
        if (!aktualnyLevel.jeTamStena(this.surX, this.surY - 1)) {
            this.surY--;
            return true;
        }
        return false;
    }

    public boolean posunDole(Level aktualnyLevel) {
        if (!aktualnyLevel.jeTamStena(this.surX, this.surY + 1)) {
            this.surY++;
            return true;
        }
        return false;
    }

    public void setTextura(Texture textura) {
        this.textura = textura;
    }
    

}
