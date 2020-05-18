package Assignment4;

import java.util.ArrayList;

public class Reader extends Thread {

    private Controller controller;
    private BoundedBuffer buffer;
    private String newText;
    private String replace;
    private int size;

    public Reader(BoundedBuffer buffer, Controller controller, String replace, int size) {
        this.controller = controller;
        this.replace = replace;
        this.buffer = buffer;
        this.newText = "";
        this.size = size;
        start();
    }

    /**
     * Reads the Strings from the buffer and fins the matches and then writes the amount of matches.
     */
    @Override
    public void run() {
        try {
            // Read the texts from the buffer
            for (int i = 0; i < size; i++) {
                newText += buffer.read() + "\n";
                controller.writeDestination(newText);
            }

            String[] amt = newText.split(" ");
            int count = 0;
            for(String s : amt) {
                if (s.contains(replace)){
                    count++;
                }
            }
            // Writes the number of matching snippets in the window
            controller.setMatchingCount(count);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
