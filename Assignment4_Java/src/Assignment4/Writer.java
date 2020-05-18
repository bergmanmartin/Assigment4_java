package Assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Writer extends Thread {

    private ArrayList<String> writeData;
    private BoundedBuffer buffer;

    public Writer(BoundedBuffer buffer, ArrayList<String> writeData) {
        this.buffer = buffer;
        this.writeData = writeData;
        start();
    }

    @Override
    public void run() {
        try {
            for (String s : writeData) {
                buffer.write(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
