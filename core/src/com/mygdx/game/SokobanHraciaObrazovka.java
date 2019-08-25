package com.mygdx.game;

import com.mygdx.game.levely.Level;
import game_items.HlavaSokobana;
import com.mygdx.game.levely.Level1;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.levely.EditorskyLevel;
import com.mygdx.game.levely.Level2;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class SokobanHraciaObrazovka extends ApplicationAdapter {

    SpriteBatch batch;
    private HlavaSokobana sokoban;
    private Level aktualnyLevel;
    private ArrayList<Level> zoznamLevelov;
    private int pocitadloVyhratychLevelov;
    private String menoHraca;
    private int zvolenyLevel;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.sokoban = new HlavaSokobana(this.batch);
        this.zoznamLevelov = new ArrayList<Level>();
        this.zoznamLevelov.add(new Level1(this.batch));
        this.zoznamLevelov.add(new Level2(this.batch));
        File subor = new File("tmp.txt");
        try {
            Scanner citac = new Scanner(subor);
            this.menoHraca = citac.nextLine();
            this.zvolenyLevel = citac.nextInt();

        } catch (FileNotFoundException ex) {
            System.out.println("Súbor nenájdený");
        }

        try {
            File edit = new File("vlastnyLevel.ser");
            ObjectInputStream j = new ObjectInputStream(new FileInputStream(edit));
            EditorskyLevel obj = (EditorskyLevel) j.readObject();
            obj.setMapaLevelu(new Texture("editor.jpg"));
            obj.priradTexturyNaspat(this.batch);
            obj.setSiEditovany(false);
            this.zoznamLevelov.add(obj);
        } catch (FileNotFoundException ex) {
            System.out.println("1");
        } catch (IOException ex) {
            System.out.println("2");
        } catch (ClassNotFoundException ex) {
            System.out.println("3");
        }

        this.aktualnyLevel = this.zoznamLevelov.get(this.zvolenyLevel - 1);
        this.pocitadloVyhratychLevelov = 0;
        this.sokoban.setSurX(this.aktualnyLevel.getDefaultX());
        this.sokoban.setSurY(this.aktualnyLevel.getDefaultY());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (this.aktualnyLevel.siVyhraty()) {
            if (this.pocitadloVyhratychLevelov < this.zoznamLevelov.size()) {
                this.pocitadloVyhratychLevelov++;
            } else {
                JOptionPane.showMessageDialog(null, "Gratulujem " + this.menoHraca + ", presiel/presla si celú hru !");
                System.exit(0);
            }

            this.aktualnyLevel = this.zoznamLevelov.get(this.pocitadloVyhratychLevelov - 1);
            this.sokoban.setSurX(this.aktualnyLevel.getDefaultX());
            this.sokoban.setSurY(this.aktualnyLevel.getDefaultY());
        }
        this.aktualnyLevel.vykresliLevel(this.batch);
        this.sokoban.vykresliSa();

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            this.sokoban.posunVpravo(this.aktualnyLevel);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            this.sokoban.posunVlavo(this.aktualnyLevel);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            this.sokoban.posunHore(this.aktualnyLevel);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {

            this.sokoban.posunDole(this.aktualnyLevel);
        }

        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public void getInput(String meno, int level) {
        System.out.println(meno + " " + level);
        //this.aktualnyLevel = this.zoznamLevelov.get(level-1);
    }

    public void reset() {
        if (this.aktualnyLevel instanceof Level1) {
            this.aktualnyLevel = new Level1(this.batch);
        }
        if (this.aktualnyLevel instanceof Level2) {
            this.aktualnyLevel = new Level2(this.batch);
        }
        //this.aktualnyLevel = this.zoznamLevelov.get(this.zvolenyLevel - 1 + this.pocitadloVyhratychLevelov);
        this.sokoban.setSurX(this.aktualnyLevel.getDefaultX());
        this.sokoban.setSurY(this.aktualnyLevel.getDefaultY());
    }
}
