import java.util.Scanner;

public class problem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку:");
        String input = scanner.nextLine();

        // "\\s+" означает, что слова разделяются одним или несколькими пробельными символами
        String[] words = input.split("\\s+");

        System.out.println("Слова с цифрами или символами пунктуации:");

        for (int i = 0; i < words.length; i++) {
            String word = words[i];  // Получаем текущее слово из массива
            for (int j = 0; j < word.length(); j++) {
                char ch = word.charAt(j);  // Получаем текущий символ из слова
                if (Character.isDigit(ch) || !Character.isLetterOrDigit(ch)) {
                    System.out.println(word);  // Если найдена цифра или символ пунктуации, выводим слово
                    break;
                }
            }
        }
    }
}
