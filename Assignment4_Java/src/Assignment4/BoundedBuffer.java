package Assignment4;

public class BoundedBuffer {
    private int bufferSize;

    private String[] textBuffer;
    private Status[] bufferStatus;

    private int readPos = 0;
    private int writePos = 0;
    private int checkPos = 0;


    public BoundedBuffer(int size) {
        this.bufferSize = size;
        textBuffer = new String[size];
        bufferStatus = new Status[size];

        for (int i = 0; i < bufferStatus.length; i++) {
            bufferStatus[i] = Status.Empty;
        }
    }




    public void write(String data) throws InterruptedException {

        synchronized (this)
        {
            try {
                while (bufferStatus[writePos] != Status.Empty) {
                    wait();
                }
                // Writes and then sets the status and increases the position.
                textBuffer[writePos] = data;
                bufferStatus[writePos] = Status.New;
                writePos = ((writePos + 1) % bufferSize);

                notifyAll();    // Signals to the other threads that changes have been made
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public boolean modifyData(String find, String replacement) {
        synchronized (this) {
            boolean found = false;
            try {
                while(bufferStatus[checkPos] != Status.New) {
                    wait();
                }

                if (textBuffer[checkPos].contains(find)) {
                    textBuffer[checkPos] = textBuffer[checkPos].replace(find, replacement);
                    found = true;
                }
                bufferStatus[checkPos] = Status.Checked;
                checkPos = ((checkPos + 1) % bufferSize);
                notifyAll();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return found;
        }
    }

    public String read() throws InterruptedException {


        synchronized (this) {
            String readString = "";
            try {
                while (bufferStatus[readPos] != Status.Checked) {
                    wait();
                }

                readString = textBuffer[readPos];
                bufferStatus[readPos] = Status.Empty;
                readPos = ((readPos + 1) % bufferSize);
                notifyAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return readString;
        }
    }

}
