package Assignment4;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Controller {

    private MonitorGUI gui;
    private BoundedBuffer buffer;
    private Writer writer;
    private Modifier modifier;
    private Reader reader;

    private JTextPane destination;

    private File file;

    public Controller() {
        gui = new MonitorGUI(this);
    }

    /**
     * Reads the from the file stream and  adds to the list.
     * @param file
     */
    public void readFile(File file) {
        this.file = file;

        ArrayList<String> list = new ArrayList<>();
        // Reads content from the list
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
            String read;
            Iterator<String> iter = list.iterator();
            while (iter.hasNext()) {
                read = iter.next();
                list.add(read);
            }


        } catch (IOException ie) {
            ie.printStackTrace();
        }
        // New lines
        String text = "";
        for (String s : list) {
            text += s + "\n";
        }

        gui.setText(text);


    }

    public void replace(String text, String find, String replace, JTextPane destination) {
        this.destination = destination;

        String [] split = text.split("\n}");

    }




    public void writeDestination(String newText) {
        destination.setText(newText);
    }

    public void setMatchingCount(int i) {
        gui.setFoundCount(i);
        
    }




}
