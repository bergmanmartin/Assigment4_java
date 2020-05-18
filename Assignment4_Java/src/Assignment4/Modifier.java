package Assignment4;

public class Modifier extends Thread {
    private BoundedBuffer buffer;
    private int arrayLength;

    private String find;
    private String replacement;

    private Controller controller;


    public Modifier(BoundedBuffer buffer, Controller controller, int arrayLength, String find, String replacement){
        this.buffer = buffer;
        this.arrayLength = arrayLength;
        this.controller = controller;
        this.find = find;
        this.replacement = replacement;
    }

    public void run() {
        modify();
    }

    /**
     * Modify data in the buffer. Performed for each sentence in the source text.
     */
    public void modify(){
        for(int i = 0; i < arrayLength; i++) {
            buffer.modifyData(find, replacement);
        }
    }
}

