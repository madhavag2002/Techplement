import java.util.*;

public class QuizGenerator {
    private static final Map<String, List<Question>> quizzes = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Quiz Generator ---");
            System.out.println("1. Create a new quiz");
            System.out.println("2. Add a question to a quiz");
            System.out.println("3. Take a quiz");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> createQuiz(scanner);
                case 2 -> addQuestion(scanner);
                case 3 -> takeQuiz(scanner);
                case 4 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createQuiz(Scanner scanner) {
        System.out.print("Enter quiz name: ");
        String quizName = scanner.nextLine();

        if (quizzes.containsKey(quizName)) {
            System.out.println("Quiz already exists.");
        } else {
            quizzes.put(quizName, new ArrayList<>());
            System.out.println("Quiz created successfully!");
        }
    }

    private static void addQuestion(Scanner scanner) {
        System.out.print("Enter quiz name: ");
        String quizName = scanner.nextLine();

        if (!quizzes.containsKey(quizName)) {
            System.out.println("Quiz does not exist.");
            return;
        }

        System.out.print("Enter the question: ");
        String questionText = scanner.nextLine();

        List<String> options = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            System.out.print("Enter option " + i + ": ");
            options.add(scanner.nextLine());
        }

        System.out.print("Enter the number of the correct option (1-4): ");
        int correctOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (correctOption < 1 || correctOption > 4) {
            System.out.println("Invalid correct option number. Question not added.");
            return;
        }

        Question question = new Question(questionText, options, correctOption);
        quizzes.get(quizName).add(question);
        System.out.println("Question added successfully to quiz '" + quizName + "'.");
    }

    private static void takeQuiz(Scanner scanner) {
        System.out.print("Enter quiz name: ");
        String quizName = scanner.nextLine();

        if (!quizzes.containsKey(quizName) || quizzes.get(quizName).isEmpty()) {
            System.out.println("Quiz does not exist or has no questions.");
            return;
        }

        List<Question> questions = quizzes.get(quizName);
        int score = 0;

        System.out.println("\nStarting quiz: " + quizName);
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.getQuestionText());

            List<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }

            System.out.print("Your answer: ");
            int answer = scanner.nextInt();

            if (answer == question.getCorrectOption()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer was " + question.getCorrectOption());
            }
        }

        System.out.println("\nQuiz finished! Your score: " + score + "/" + questions.size());
    }
}


 
