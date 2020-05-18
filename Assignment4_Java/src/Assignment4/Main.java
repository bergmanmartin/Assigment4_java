package Assignment4;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main (String [] args)
    {
        Controller controller = new Controller();
        MonitorGUI gui = new MonitorGUI(controller);
        gui.Start();

        try {
            Scanner sc = new Scanner(System.in);
            String str =sc.nextLine();
        }
        catch (Exception e)
        {
        }
    }

}
