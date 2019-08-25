package game_items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.levely.Level;
import com.mygdx.game.SmerRotacie;

/**
 *
 * @author David
 */
public class HlavaSokobana extends Predmet {

    private Texture hlava;
    private int surX;
    private int surY;
    private Sprite sprite;

    public HlavaSokobana(SpriteBatch batch) {
        super(new Texture("hlava.png"), 4, 4, batch);
        this.hlava = new Texture("hlava.png");
        this.surX = 4;
        this.surY = 4;
        this.sprite = new Sprite(this.hlava);
        this.sprite.setSize(64, 64);
    }

    public void setSurX(int surX) {
        this.surX = surX;
    }

    public void setSurY(int surY) {
        this.surY = surY;
    }

    public void rotuj(SmerRotacie smer) {
        switch (smer) {
            case VPRAVO:
                if (this.sprite.getRotation() != -90) {
                    this.sprite.setRotation(-90);
                }
                break;
            case VLAVO:
                if (this.sprite.getRotation() != 90) {
                    this.sprite.setRotation(90);
                }
                break;
            case HORE:
                if (this.sprite.getRotation() != 0) {
                    this.sprite.setRotation(0);
                }
                break;
            case DOLE:
                if (this.sprite.getRotation() != -180) {
                    this.sprite.setRotation(-180);
                }
                break;

        }
    }

    public void vykresliSa() {
        int y = Math.abs(this.surY * this.hlava.getHeight() - Gdx.graphics.getHeight()) - this.hlava.getHeight();
        this.sprite.setPosition(this.surX * this.hlava.getWidth(), y);
        this.sprite.draw(super.getBatch());

    }

    @Override
    public boolean posunVpravo(Level aktualnyLevel) {
        if (!aktualnyLevel.jeTamStena(this.surX + 1, this.surY)) {
            if (aktualnyLevel.jeTamKrabica(this.surX + 1, this.surY) != null) {

                if (!aktualnyLevel.jeTamKrabica(this.surX + 1, this.surY).posunVpravo(aktualnyLevel)) {
                    return false;
                }
            }
            this.surX++;
            this.rotuj(SmerRotacie.VPRAVO);

        }
        return true;
    }

    @Override
    public boolean posunVlavo(Level aktualnyLevel) {
        if (!aktualnyLevel.jeTamStena(this.surX - 1, this.surY)) {
            if (aktualnyLevel.jeTamKrabica(this.surX - 1, this.surY) != null) {

                if (!aktualnyLevel.jeTamKrabica(this.surX - 1, this.surY).posunVlavo(aktualnyLevel)) {
                    return false;
                }

            }
            this.surX--;
            this.rotuj(SmerRotacie.VLAVO);
        }
        return true;
    }

    @Override
    public boolean posunHore(Level aktualnyLevel) {
        if (!aktualnyLevel.jeTamStena(this.surX, this.surY - 1)) {
            if (aktualnyLevel.jeTamKrabica(this.surX, this.surY - 1) != null) {

                if (!aktualnyLevel.jeTamKrabica(this.surX, this.surY - 1).posunHore(aktualnyLevel)) {
                    return false;
                }
            }
            this.surY--;
            this.rotuj(SmerRotacie.HORE);
        }
        return true;
    }

    @Override
    public boolean posunDole(Level aktualnyLevel) {
        if (!aktualnyLevel.jeTamStena(this.surX, this.surY + 1)) {
            if (aktualnyLevel.jeTamKrabica(this.surX, this.surY + 1) != null) {

                if (!aktualnyLevel.jeTamKrabica(this.surX, this.surY + 1).posunDole(aktualnyLevel)) {
                    return false;
                }
            }
            this.surY++;
            this.rotuj(SmerRotacie.DOLE);
        }
        return true;
    }

}
