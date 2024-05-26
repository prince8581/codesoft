import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctAnswerIndex;

    public QuizQuestion(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

public class QuizProgram {
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static QuizQuestion[] quizQuestions;
    private static Timer timer;

    public static void main(String[] args) {
        initializeQuiz();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz!");
        System.out.println("You will have 10 seconds to answer each question.");

        while (currentQuestionIndex < quizQuestions.length) {
            displayQuestion();
            startTimer();

            int userAnswer = scanner.nextInt();

            if (userAnswer == quizQuestions[currentQuestionIndex].getCorrectAnswerIndex()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect.");
            }

            cancelTimer();

            currentQuestionIndex++;
        }

        scanner.close();

        displayResult();
    }

    private static void initializeQuiz() {
        quizQuestions = new QuizQuestion[5];

        quizQuestions[0] = new QuizQuestion("What is the capital of France?",
                new String[]{"1. London", "2. Paris", "3. Rome", "4. Berlin"}, 1);

        quizQuestions[1] = new QuizQuestion("Which planet is known as the Red Planet?",
                new String[]{"1. Mars", "2. Venus", "3. Jupiter", "4. Saturn"}, 0);

        quizQuestions[2] = new QuizQuestion("What is the powerhouse of the cell?",
                new String[]{"1. Nucleus", "2. Ribosome", "3. Mitochondria", "4. Golgi Apparatus"}, 2);

        quizQuestions[3] = new QuizQuestion("Who painted the Mona Lisa?",
                new String[]{"1. Michelangelo", "2. Leonardo da Vinci", "3. Vincent van Gogh", "4. Pablo Picasso"}, 1);

        quizQuestions[4] = new QuizQuestion("What is the chemical symbol for water?",
                new String[]{"1. H2O", "2. CO2", "3. NaCl", "4. O2"}, 0);
    }

    private static void displayQuestion() {
        QuizQuestion currentQuestion = quizQuestions[currentQuestionIndex];
        System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());
        for (String option : currentQuestion.getOptions()) {
            System.out.println(option);
        }
        System.out.print("Your answer: ");
    }

    private static void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                cancelTimer();
            }
        }, 10000); // 10 seconds
    }

    private static void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    private static void displayResult() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your score: " + score + "/" + quizQuestions.length);
    }
}

