package com.mygdx.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author David
 */
public class Reader1 {

    private boolean[][] polePrekazok;

    public Reader1(String cestaSuboru) throws FileNotFoundException, IOException {

        this.polePrekazok = new boolean [9][9];
        FileReader fr = new FileReader(cestaSuboru);
        try {
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                String[] pole = line.split(",", 2);
                this.polePrekazok[Integer.parseInt(pole[0])][Integer.parseInt(pole[1])] = true;
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean[][] getPolePrekazok() {
        return this.polePrekazok;
    }
    
    
}


