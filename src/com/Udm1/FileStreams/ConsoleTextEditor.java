package com.Udm1.FileStreams;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleTextEditor {
    public final String EXIT_COMMAND = "exit";
    File destinationFile;

    ConsoleTextEditor(File fileToWrite) {
        if (fileToWrite == null) {
            System.out.println("Can't write without destination file");
            return;
        }
        this.destinationFile = fileToWrite;
    }

    public void start() {
        List<String> inputText = new ArrayList<>();
        String userInput = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter text. Write \"exit\" to stop. " +
                "Your text will be saved to file after exit.");
        while (true) {
            userInput = scanner.next();
            if (userInput.equalsIgnoreCase(EXIT_COMMAND)) {
                break;
            }
            inputText.add(userInput);
        }
        try {
            writeToFile(inputText);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void writeToFile(List<String> inputText) throws IOException {
        try (var inputWriter = (new BufferedWriter(new FileWriter(destinationFile)))) {
            for (String element:
                 inputText) {
                inputWriter.write(element + "\n");
            }
            inputWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}