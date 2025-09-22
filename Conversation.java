import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

class Conversation implements ConversationRequirements {

  // Attributes 
  Scanner scanner;
  List <String> transcript;
  String [] replies;
  String [] detectedWords;
  String [] changeWord;

  /**
   * Constructor 
   */
  Conversation(List<String> history, String[] fixed_replies, String[] fromwords, String[] towords) {
    this.scanner = new Scanner(System.in);
    this.transcript = history;
    this.replies = fixed_replies;
    this.detectedWords = fromwords;
    this.changeWord = towords;
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    System.out.println("Hello! Welcome to Amy and Cola's chat bot!");
    System.out.println("How many rounds would you like to talk to us :)");

    int n = scanner.nextInt();
    scanner.nextLine();
    System.out.println("What's on your mind?");

    for (int i = 0; i < n; i++){
      String user_input = scanner.nextLine();

      transcript.add("User: "+ user_input);
      String bot_respond = respond(user_input);
      System.out.println(bot_respond);
      transcript.add("Bot:"+ bot_respond);
    }
    System.out.println("See ya!");
  }


  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("\nTranscript:");
    for (int i = 0; i < transcript.size(); i++){
      System.out.println(transcript.get(i));
    }
  }


  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    Boolean replaced = false;
    String[] respond_list =  inputString.split(" ");

    for (int j = 0; j < respond_list.length; j++) {
      String respond_words = respond_list[j].toLowerCase();
      for (int i = 0; i < detectedWords.length; i++) {
        String from = detectedWords[i].toLowerCase();
          if (respond_words.equals(from)) {
              respond_list[j] = changeWord[i];
              replaced = true;
              }
          }
      }

    if (replaced){
      String result = String.join(" ", respond_list);
      return result;
    }else{
        Random rand = new Random();
        int index = rand.nextInt(replies.length);
        return (replies[index]);
    }
  }


  public static void main(String[] arguments) {
    List<String> history = new ArrayList<>();
    String[] fixed_replies = new String[]{"Mmm-hm", "That's great", "Interesting", "I see...", "I want know more about this"};
    String[] fromwords = new String[]{"I","me", "am", "you", "my", "your", "are"};
    String[] towords   = new String[]{"you","you", "are", "me","your", "my", "is"};
    Conversation myConversation = new Conversation(history, fixed_replies, fromwords, towords);
    myConversation.chat();
    myConversation.printTranscript();
  }
}
