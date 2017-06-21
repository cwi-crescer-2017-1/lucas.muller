package br.com.crescer.aula2;

import br.com.crescer.aula1.IStringUtils;
import br.com.crescer.aula1.StringUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author carloshenrique
 */
public class Chat {

    private static final Logger LOGGER = Logger.getAnonymousLogger();
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final StringUtils STRING_UTILS = new StringUtils();

    public static BufferedReader getReader(String fila) throws FileNotFoundException, IOException {
        File file = new File(fila);
        file.createNewFile();
        return new BufferedReader(new FileReader(file));
    }

    public static BufferedWriter getWriter(String fila) throws IOException {
        return new BufferedWriter(new FileWriter(fila, true));
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            args = new String[]{"\\\\10.0.100.102\\cwitmp\\carloshenrique\\aula.txt", "@somebody"};
        }

        final String fileName = args[0];
        final String nickName = args[1];

        new Thread(new Runnable() {
            @Override
            public void run() {

                BufferedReader bufferReader = null;
                try {
                    bufferReader = Chat.getReader(fileName);
                    while (true) {
                        String readLine = bufferReader.readLine();
                        if (!STRING_UTILS.isEmpty(readLine)) {
                            System.out.println(readLine);
                        }
                        // Tempo de espera para ler o buffer.
                        Thread.sleep(500l);
                    }
                } catch (InterruptedException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage());
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage());
                } finally {
                    try {
                        if (bufferReader != null) {
                            bufferReader.close();
                        }
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, e.getMessage());
                    }
                }

            }
        }
        ).start();

        while (true) {
            System.out.println("[DIGITE]: ");
            Scanner scanner = new Scanner(System.in);
            String nextLine = scanner.nextLine();
            if (":q!".equals(nextLine)) {
                break;
            }
            BufferedWriter bufferedWriter = null;
            try {
                bufferedWriter = getWriter(fileName);
                bufferedWriter.append(DATE_FORMAT.format(new Date()));
                bufferedWriter.append(" - ").append(nickName).append(" - ").append(nextLine);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            } finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, e.getMessage());
                }
            }
        }
    }
}