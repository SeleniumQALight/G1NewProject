package loginTests;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class testAnalizer {

    @Test
    public void testTest() throws IOException {
//        String pathToFolder = "D:\\workSpace\\QaLight\\G1Project\\G1NewProject\\overAll\\src\\test\\java\\loginTests";
//        String pathToFolder = "D:\\workSpace\\CTC_ORKE_NEW\\frontier_autotests\\serenity-steps-parent\\serenity-steps" +
//                "-ui-equity\\src\\main\\java\\com\\frontier\\ta\\steps\\ui\\equity\\apsl";
//        String pathToFolder = "D:\\workSpace\\CTC_ORKE_NEW\\frontier_autotests\\serenity-steps-parent\\serenity
//        -steps" +
//                "-ui-equity\\src\\main\\java\\com\\frontier\\ta\\steps\\ui\\equity";
        String pathToFolder = "D:\\workSpace\\CTC_ORKE_NEW\\frontier_autotests";

        List<Path> listPathOfFiles = Files.walk(Paths.get(pathToFolder))
                .filter(Files::isRegularFile)
                .filter(a -> a.toString().contains(".java"))
                .collect(Collectors.toList());
        System.out.println("---------------");

//        String filePath = "D:\\workSpace\\QaLight\\G1Project\\G1NewProject\\overAll\\src\\test\\java\\loginTests" +
//                "\\StoryOr188147.java";


//        String filePath = listPathOfFiles.get(0).toString();
        int counter = 0;
        for (Path filePath : listPathOfFiles) {


            String linesFromFile = readLineByLineJava8(filePath.toString()).toLowerCase();
//        System.out.println( linesFromFile );


            List<String> list = Arrays.stream(linesFromFile.split(";"))
                    .filter(a -> a.contains("assertthat"))
                    .filter(a -> !a.contains("import"))
                    .filter(a -> !a.contains(".doesnotcontain"))
                    .filter(a -> !a.contains(".doesnotmatch"))
                    .filter(a -> !a.contains(".contains"))
                    .filter(a -> !a.contains(".endswith"))
                    .filter(a -> !a.contains(".matches"))
                    .filter(a -> !a.contains(".fail"))
                    .filter(a -> a.contains("soft"))
                    .filter(a -> (!a.contains(".is") && !a.contains("size")))
                    .collect(Collectors.toList());

            if (list.size() > 0) {
                System.out.println("-------");
                System.out.println("File : " + filePath);
                System.out.println("List size = " + list.size());
                list.forEach(a -> System.out.println(a));
            }
            counter++;
        }
        System.out.println("All files = " + counter);
    }

    @Test
    public void testSoftAssert() throws IOException {
//        String pathToFolder = "D:\\workSpace\\QaLight\\G1Project\\G1NewProject\\overAll\\src\\test\\java\\loginTests";
//        String pathToFolder = "D:\\workSpace\\CTC_ORKE_NEW\\frontier_autotests\\serenity-steps-parent\\serenity-steps" +
//                "-ui-equity\\src\\main\\java\\com\\frontier\\ta\\steps\\ui\\equity\\apsl";
//        String pathToFolder = "D:\\workSpace\\CTC_ORKE_NEW\\frontier_autotests\\serenity-steps-parent\\serenity-steps" +
//                "-ui-equity\\src\\main\\java\\com\\frontier\\ta\\steps\\ui\\equity";
        String pathToFolder = "D:\\workSpace\\CTC_ORKE_NEW\\frontier_autotests";

        List<Path> listPathOfFiles = Files.walk(Paths.get(pathToFolder))
                .filter(Files::isRegularFile)
                .filter(a -> a.toString().contains(".java"))
                .collect(Collectors.toList());
        System.out.println("---------------");

        int counter = 0;
        for (Path filePath : listPathOfFiles) {

            String linesFromFile = readLineByLineJava8(filePath.toString()).toLowerCase();

            List<String> list = Arrays.stream(linesFromFile.split("@step"))
                    .filter(a -> a.contains("assertthat"))
                    .filter(a -> !a.contains("import"))
//                    .filter(a -> !a.contains(".doesnotcontain"))
//                    .filter(a -> !a.contains(".doesnotmatch"))
//                    .filter(a -> !a.contains(".contains"))
//                    .filter(a -> !a.contains(".endswith"))
//                    .filter(a -> !a.contains(".matches"))
//                    .filter(a -> !a.contains(".fail"))
                    .filter(a -> a.contains("softass"))
                    .filter(a -> !a.contains("assertions.assertall()".toLowerCase(Locale.ROOT)))
//                    .filter(a -> !a.contains("softassert.assertall()".toLowerCase(Locale.ROOT)))
                    .filter(a -> !a.contains("private void".toLowerCase(Locale.ROOT)))
//                    .filter(a -> (!a.contains(".is") && !a.contains("size")))
                    .collect(Collectors.toList());

            if (list.size() > 0) {
                System.out.println("-------");
                System.out.println("File : " + filePath);
                System.out.println("List size = " + list.size());
                list.forEach(a -> System.out.println(a));
            }
            counter++;
        }
        System.out.println("All files = " + counter);
    }

    @Test
    public void testLambdaAssert() throws IOException {
//        String pathToFolder = "D:\\workSpace\\QaLight\\G1Project\\G1NewProject\\overAll\\src\\test\\java\\loginTests";
        String pathToFolder = "D:\\workSpace\\CTC_ORKE_NEW\\frontier_autotests\\serenity-steps-parent\\serenity-steps" +
                "-ui-equity\\src\\main\\java\\com\\frontier\\ta\\steps\\ui\\equity\\apsl";
//        String pathToFolder = "D:\\workSpace\\CTC_ORKE_NEW\\frontier_autotests\\serenity-steps-parent\\serenity-steps" +
//                "-ui-equity\\src\\main\\java\\com\\frontier\\ta\\steps\\ui\\equity";
//        String pathToFolder = "D:\\workSpace\\CTC_ORKE_NEW\\frontier_autotests";

        List<Path> listPathOfFiles = Files.walk(Paths.get(pathToFolder))
                .filter(Files::isRegularFile)
                .filter(a -> a.toString().contains(".java"))
                .collect(Collectors.toList());
        System.out.println("---------------");

        int counter = 0;
        for (Path filePath : listPathOfFiles) {

            String linesFromFile = readLineByLineJava8(filePath.toString());

            List<String> list = Arrays.stream(linesFromFile.split("@step"))
                    .filter(a -> a.contains("->"))
                    .filter(a -> a.contains("assert"))
                    .filter(a -> !a.contains("import"))
//                    .filter(a -> !a.contains(".doesnotcontain"))
//                    .filter(a -> !a.contains(".doesnotmatch"))
//                    .filter(a -> !a.contains(".contains"))
//                    .filter(a -> !a.contains(".endswith"))
//                    .filter(a -> !a.contains(".matches"))
//                    .filter(a -> !a.contains(".fail"))
//                    .filter(a -> a.contains("softass"))
//                    .filter(a -> !a.contains("assertions.assertall()".toLowerCase(Locale.ROOT)))
//                    .filter(a -> !a.contains("softassert.assertall()".toLowerCase(Locale.ROOT)))
//                    .filter(a -> !a.contains("private void".toLowerCase(Locale.ROOT)))
//                    .filter(a -> (!a.contains(".is") && !a.contains("size")))
                    .collect(Collectors.toList());

            if (list.size() > 0) {
                System.out.println("-------");
                System.out.println("File : " + filePath);
                System.out.println("List size = " + list.size());
                list.forEach(a -> System.out.println(a));
            }
            counter++;
        }
        System.out.println("All files = " + counter);
    }

    //Read file content into the string with - Files.lines(Path path, Charset cs)

    private static String readLineByLineJava8(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString().replace("//", "").toLowerCase(Locale.ROOT);
    }

    @Test
    public void filesCountInFolder() throws IOException {
        String pathToFolder = "D:\\workSpace\\CTC_ORKE_NEW\\frontier_autotests\\all-tests-parent\\serenity-bdd-tests" +
                "\\src\\test\\java\\com";


        List<Path> listPathOfFiles = Files.walk(Paths.get(pathToFolder))
                .filter(Files::isRegularFile)
            //    .filter(a -> a.toString().contains(".java"))
                .collect(Collectors.toList());
        System.out.println("Number of files = " + listPathOfFiles.size());
    }


    @Test
    public void testString(){
        String var = new String("test");
        String var1 = new String("test");
        boolean isEq = var == var1;
        boolean isEquals = var.equals(var1);

        StringBuffer reversString = new StringBuffer(var);
        reversString.reverse();

        System.out.println(reversString);


        System.out.println(isEq + " " + isEquals);
        /*
        ДЗ№3
“   Test_String ”
1.  Убрать пробелы в начале и в конце стринги
2.  С указанной стринги оставить только слово Test
3.  Сделать проверку – если первый Не пустой символ строки ‘t’ или ‘T’ то вывести сообщениее «Первый символ - «Т»» если нет – то ничего не делать.
4.  Найти первую букву ‘s’ и заменить ее на  ‘y’
Это тренировка по работе со стрингами, поэтому делать ее можно прямо в мейне. Каждый пункт - это отдельное новое действие с исходной стрингой.
         */
    }

}
