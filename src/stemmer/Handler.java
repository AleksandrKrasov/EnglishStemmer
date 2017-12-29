/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stemmer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Саня
 */
public class Handler {

    String word;
    

    public Handler(String word) {
        this.word = word;
    }

    private void write() {
        try {
            PrintWriter pw = new PrintWriter("res/buffer.txt");
            pw.print(word);
            pw.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File not found");
        }

    }

    public ArrayList<String> handle() {
        write();
        char[] w = new char[501];
        Stemmer s = new Stemmer();
        ArrayList<String> text = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            try {
                FileInputStream in = new FileInputStream("res/buffer.txt");
                try {
                    while (true) {
                        int ch = in.read();
                        if (Character.isLetter((char) ch)) {
                            int j = 0;
                            while (true) {
                                ch = Character.toLowerCase((char) ch);
                                w[j] = (char) ch;
                                if (j < 500) {
                                    j++;
                                }
                                ch = in.read();
                                if (!Character.isLetter((char) ch)) {
                                    for (int c = 0; c < j; c++) {
                                        s.add(w[c]);
                                    }

                                    s.stem();
                                    {
                                        String u;
                                        u = s.toString();
                                        if (u.length() > 1) {
                                            text.add(u);
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        if (ch < 0) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "error reading");
                    break;
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "File not found");
                break;
            }
        }
        return text;
    }
}
