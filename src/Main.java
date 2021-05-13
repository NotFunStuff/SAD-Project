import Controller.ControllerModel;

import java.io.IOException;

public class Main {

    public static ControllerModel CM;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CM = new ControllerModel();
        CM.loadFromFile();
        CM.makeLoginFrame();
    }
}
