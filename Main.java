import java.util.*;
import java.io.*;
import java.lang.Math;

//Thank you to Source of hangman words: https://github.com/Xethron/Hangman/blob/master/words.txt Xethron github

class Main {

  static ArrayList<Character> alreadyGuessed = new ArrayList<Character>();
  static ArrayList<String> lettersList = new ArrayList<String>();
  static ArrayList<Character> dynamic = new ArrayList<Character>();

  static String[] words;
  static char[] chars;
  static char[] yesChars;
  static String word;
  static int numOfLines = 0;
  static Scanner scan = new Scanner(System.in);
  static int lives = 7;
  static boolean win = false;
  static String placeholder = "";
  static String letter;
  static int currentIndex = 0;
  static char currentLetter;

  public static void main(String[] args) throws IOException {
    System.out.println("Welcome to Hangman!");
    readListWords();
    pickWord();
    convertCharArray();
    dynamic();
    System.out.println(word);
    play();
    // askUser();
  }
static int b = 0;
  public static void fill(){
    b++;
    System.out.println(b);
    for(int j = 0; j<chars.length; j++){
      for(int i = 0; i<yesChars.length; i++){
        if(yesChars[i] == chars[j]){
          dynamic.set(j, yesChars[i]);

          // System.out.println(i + ", " + j + ", " + currentLetter);
        }
      }
      j++;
    }
  }

  public static void dynamic(){
    for(String a: lettersList){
      dynamic.add('_');
    }
  }

  public static void play() {

    clearScreen();
    System.out.println();

    // for(String a: lettersList){
    //   System.out.print(a + ",");
    // }
    // for(char a: chars){
    //   System.out.print(a);
    // }

    if(!placeholder.equals("")){
      System.out.println("\n" + placeholder + "\n");
    }
    
    System.out.print("\t");
    // System.out.println(alreadyGuessed);
    
    // for (int i = 0; i < word.length(); i++) {
    //   System.out.print("___ ");
    // }

    for(char s: dynamic){
      System.out.print(s);
    }
    // System.out.println();
    // for(char c: chars){
    //   System.out.print(c);
    // }

    System.out.println("\n" + word);

    System.out.print("❌:");
    for(char c: alreadyGuessed){
      System.out.print(" " + c);
    }

    System.out.println("\n");
    System.out.print("Lives: ");
    for(int i = 0; i< lives; i++){
      System.out.print(" ❤️ ");
    }
    System.out.print("\n\n");
    askUser();
    System.out.println(currentLetter); //user input
    fill();
    
    if(!win){
      askUser();
    }
  }

  public static void convertCharArray(){
    for(char c: chars){
      lettersList.add(String.valueOf(c));
    }
  }

  public static void printStuff(){
    for(int i = 0; i<200; i++){
      System.out.println("hi");
    }
  }
  
  public static void askUser() {
    placeholder = "";
    System.out.print("Letter: ");
    currentLetter = scan.nextLine().charAt(0);
    boolean match = false;

    boolean duplicate = false;
    for(char b: alreadyGuessed){
      if(b == currentLetter || Math.abs(b - currentLetter) == 32){
        duplicate = true;
      }

      
    for(char a: chars){
      char str = a;
      if((str == currentLetter || Math.abs(str - currentLetter) == 32) && !duplicate){
        match = true;
        // printStuff();
        placeholder = "☑️☑️☑️☑️ CORRECT ☑️☑️☑️☑️";
        yesChars[currentIndex] = str;
        currentIndex++;
        
        fill();
        break;
      }
    }
    
    }

    if(!match && !duplicate){
      alreadyGuessed.add(0,currentLetter);
      lives--;
    }

    if(duplicate){
      placeholder = "You already picked that, sorry!";
    }

    System.out.println(match);

    if(!win){
      play();
    }
    
  }

  public static void pickWord() {
    word = words[(int) (Math.random() * numOfLines) + 1];
    chars = new char[word.length()];
    yesChars = new char[chars.length];
    word.getChars(0, word.length(), chars, 0);
  }

  public static void printData(){
    System.out.println("chars: ");
  }

  public static void readListWords() throws IOException {

    try {
      BufferedReader br = new BufferedReader(new FileReader("words.txt"));
      BufferedReader br2 = new BufferedReader(new FileReader("words.txt"));

      String line = br.readLine();
      String line2 = br2.readLine();

      // add words to arraylist
      while (line != null) {
        line = br.readLine();
        numOfLines++;
      }

      words = new String[numOfLines];
      int index = 0;

      while (line2 != null) {
        words[index] = line2;
        line2 = br2.readLine();
        index++;
      }

      br.close();
      br2.close();

    } catch (IOException e) {
      System.out.println("An error has occurred");
    } // catch
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

}