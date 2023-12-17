package traffic;

import java.io.IOException;

public class ClearConsoleOutput {
    public static void clearConsoleOutput() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("/bin/bash", "-c", "clear");
            clearCommand.inheritIO().start().waitFor();
        }
        catch (IOException | InterruptedException e) {}
    }
}
