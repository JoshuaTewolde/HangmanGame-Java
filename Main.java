import java.util.*;
import java.io.*;
import java.lang.Math;

//Thank you to Source of hangman words: https://github.com/Xethron/Hangman/blob/master/words.txt Xethron github

class Main {

  static ArrayList<Character> alreadyGuessed = new ArrayList<Character>();
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

  static boolean setDynamic = false;

  public static void main(String[] args) throws IOException {
    System.out.println("Welcome to Hangman!");
    readListWords();
    pickWord();
    play();
  }
  
  public static void fill(){
    for(int j = 0; j<chars.length; j++){
      for(int i = 0; i<yesChars.length; i++){
        if(yesChars[i] == chars[j]){
          dynamic.set(j, yesChars[i]);
        }
      }
      j++;
    }
  }

  public static boolean checkWin(){
    for(char c: dynamic){
      if(c == '-'){
        return false;
      }
    }
    return true;
  }

  public static void printStuff(){
    clearScreen();
    System.out.println();
    for(char c: dynamic){
      System.out.print(c + " ");
    }

    System.out.println("\n");
    System.out.print("❌:");
    for(char c: alreadyGuessed){
      System.out.print(" " + c);
    }

    System.out.println("\n");
    System.out.print("Lives: ");
    for(int i = 0; i< lives; i++){
      System.out.print(" ❤️ ");
    }
    System.out.println("\n");
  }

  public static void play() {

    clearScreen();
    System.out.println(word + "\n");

    if(!setDynamic){
      for(char c: chars){
        dynamic.add('-');
      }
      setDynamic = true;
    }
    
    //print the gaps -- dynamic
    for(char c: dynamic){
      System.out.print(c + " ");
    }

    System.out.println("\n");
    System.out.print("❌:");
    for(char c: alreadyGuessed){
      System.out.print(" " + c);
    }

    System.out.println("\n");
    System.out.print("Lives: ");
    for(int i = 0; i< lives; i++){
      System.out.print(" ❤️ ");
    }

    System.out.println();
    if(!win){
      char c = scan.next().charAt(0); 
      boolean current = false;
      for(char b: chars){
        if(b == c){
          current = true;
          break;
        }
      }

      if(current){
        dynamicUpdate(c);
        win = checkWin();
        
        if(win){
          winMessage();
        }
        else{
          play();
        }
      }
      else{
        lives--;
        alreadyGuessed.add(c);
        play();
      }

    }
  }

  public static void winMessage(){
    printStuff();
    System.out.println("Congrats! You won! Would you like to play again?");
  }

  public static void dynamicUpdate(char c){
    for(int i = 0; i<chars.length; i++){
      if(chars[i] == c){
        dynamic.set(i, c);
      }
    }
  }
    
  public static void pickWord() {
    word = words[(int) (Math.random() * numOfLines) + 1];
    chars = new char[word.length()];
    yesChars = new char[chars.length];
    word.getChars(0, word.length(), chars, 0);
    
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