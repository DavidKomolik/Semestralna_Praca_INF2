package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.levely.EditorskyLevel;
import game_items.Bomba;
import game_items.Krabica;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class EditorObrazovka extends ApplicationAdapter {

    private EditorskyLevel novy;
    private Krabica kurzor;
    SpriteBatch batch;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.novy = new EditorskyLevel(new Texture("editor.jpg"));
        this.kurzor = new Krabica(0, 0, this.batch);
        this.kurzor.setTextura(new Texture("tehla.jpg"));

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        this.novy.vykresliLevel(this.batch);
        this.kurzor.vykresliSa();

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            this.kurzor.posunVpravo(this.novy);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            this.kurzor.posunVlavo(this.novy);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            this.kurzor.posunHore(this.novy);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            this.kurzor.posunDole(this.novy);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            this.novy.pridajPrekazku(this.kurzor.getSurX(), this.kurzor.getSurY());
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            this.novy.pridajKrabicu(new Krabica(this.kurzor.getSurX(), this.kurzor.getSurY(), this.batch));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.END)) {
            this.novy.pridajBombu(new Bomba(this.kurzor.getSurX(), this.kurzor.getSurY(), this.batch));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.HOME)) {
            try {
                this.novy.ulozLevel();
            } catch (IOException ex) {
                Logger.getLogger(EditorObrazovka.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        batch.end();

    }

}
