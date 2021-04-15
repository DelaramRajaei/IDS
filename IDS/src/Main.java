import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static HashMap<String, Integer> batteries = new HashMap<String, Integer>();
    public static ArrayList<String> blocks = new ArrayList<String>();
    public static ArrayList<String> butters = new ArrayList<String>();
    public static ArrayList<String> persons = new ArrayList<String>();
    public static String robot;

    public static void main(String[] args) {

        // Initialization
        initial();

        // IDS Search
        IDSSearch();

    }

    private static void IDSSearch() {

        boolean possible = false;

        if (butters.size() != 0)
            for (int i = 0; i < butters.size(); i++) {
                AIFunction.IDS();
            }
        

    }

    private static void initial() {

        Scanner scan = new Scanner(System.in);

        int row, col;
        row = scan.nextInt();
        col = scan.nextInt();

        // Regex
        Pattern isDigit = Pattern.compile("^\\d+$");
        Pattern isBlock = Pattern.compile("^[x]{1}$");
        Pattern isEntity = Pattern.compile("^\\d+([prb])$");

        //TODO implement properly!
        String line = scan.nextLine();
        for (int i = 0; i < row; i++) {

            line = scan.nextLine();
            String[] splitted = line.split("\\t");

            for (int j = 0; j < col; j++) {

                String coordinate = i + "_" + j;

                Matcher matcher = isDigit.matcher(splitted[j]);
                if (matcher.find())
                    batteries.put(coordinate, Integer.parseInt(splitted[j]));
                else {
                    matcher = isBlock.matcher(splitted[j]);
                    if (matcher.find())
                        blocks.add(coordinate);
                    else {
                        matcher = isEntity.matcher(splitted[j]);
                        if (matcher.find()) {
                            switch (matcher.group(1)) {
                                case "r":
                                    robot = coordinate;
                                    break;
                                case "b":
                                    butters.add(coordinate);
                                    break;
                                case "p":
                                    persons.add(coordinate);
                                    break;
                            }
                        } else System.out.println("Wrong Input!");
                    }
                }
            }
        }
    }
}
