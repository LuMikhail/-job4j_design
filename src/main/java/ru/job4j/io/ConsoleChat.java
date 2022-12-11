package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс реализует программу 'Консольный чат':
 * <p>- пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * <p>- программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
 * <p>- если пользователь вводит слово «продолжить», программа снова начинает отвечать.
 * <p>- при вводе слова «закончить» программа прекращает работу.
 * <p>- запись диалога, включая слова-команды стоп/продолжить/закончить должны быть записаны в текстовый лог.
 */
public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Метод содержит логику чата.
     */
    public void run() throws IOException {
        List<String> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Random random = new Random();
        boolean check = true;
        String s = "";
        System.out.printf("С помощью этих команд: \n - %s \n - %s \n - %s \n  можно регулировать работу бота.\n",
                OUT, STOP, CONTINUE);
        System.out.println("Приступим!");
        while (!OUT.equals(s)) {
            s = reader.readLine();
            result.add(s);
            if (STOP.equals(s)) {
                check = false;
            }
            if (check) {
                String botAnswer = readPhrases().get(random.nextInt(readPhrases().size()));
                System.out.println(botAnswer);
                result.add(botAnswer);
            }
            if (CONTINUE.equals(s)) {
                check = true;
            }
        }
        saveLog(result);
    }

    /**
     * Метод читает фразы из файла.
     *
     * @return Фразы из записываются в коллекцию.
     */
    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    /**
     * Метод сохраняет лог чата в файл.
     *
     * @param log Коллекция с данными чата.
     */
    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\consolechat\\chat.txt",
                "C:\\projects\\job4j_design\\consolechat\\botAnswer.txt");
        cc.run();
    }
}
