import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

class Conversation implements ConversationRequirements {

  // Attributes
  Scanner scanner;   // the conversation should be able to reads user input from console
  List <String> transcript; // creates arraylist to store chat history
  String [] replies; // list of canned replies
  String [] detectedWords; // list of target words
  String [] changeWord; // corresponding list of changed words with target words

  /**
   * Creates a new object of conversation (a constructor)
   * @param history the conversation history
   * @param fixed_replies arraylist of canned responses
   * @param fromwords words to be detected from user input
   * @param towords changed list of words that corresponds with the detected words
   */
  Conversation(List<String> history, String[] fixed_replies, String[] fromwords, String[] towords) {
    this.scanner = new Scanner(System.in); // creates new scanner for user input
    this.transcript = history;
    this.replies = fixed_replies;
    this.detectedWords = fromwords;
    this.changeWord = towords;
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
      System.out.println("Hello! Welcome to Amy and Cola's chat bot!");//the greeting message
      System.out.println("How many rounds would you like to talk to us :)");//ask how many rounds to get int n

      int n = scanner.nextInt();//get user's input of how many rounds they would like to have
      scanner.nextLine();
      System.out.println("What's on your mind?");//To start the conversation
      transcript.add("Bot: "+ "What's on your mind?");

      for (int i = 0; i < n; i++){//for loop for the conversation
        String user_input = scanner.nextLine();//user's input

        transcript.add("User: "+ user_input);//add user's input to. the transcript
        String bot_respond = respond(user_input);//generate the respond by using respond method
        System.out.println(bot_respond + "?");//print out the respond
        transcript.add("Bot:"+ bot_respond + "?");//add the chatbox's respond to the transcript
      }
      System.out.println("See ya!");
      transcript.add("Bot: "+ "See ya!");
  }




  /**
   * Prints transcript of conversation line by line
   */
  public void printTranscript() {
    System.out.println("\nTranscript:"); // prints a header before the transcript
    for (int i = 0; i < transcript.size(); i++){ // use for loop to iterate over the list of string of transcript
      System.out.println(transcript.get(i));
    }
  }


  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    Boolean replaced = false; // first set determinant boolean expression to false
    String no_punc = inputString.replaceAll("[^a-zA-Z0-9\\s‘’'“”]", ""); // remove all non-letter characters except '',"",' from the string
    String[] respond_list =  no_punc.split(" "); // split the string into list of separated words by " "


    for (int j = 0; j < respond_list.length; j++) { // iterate over list of words converted from user input string 
      String respond_words = respond_list[j].toLowerCase(); // convert all letters to lower case to make detection more accurate
      for (int i = 0; i < detectedWords.length; i++) { //iterates over the detected words
        String from = detectedWords[i].toLowerCase(); 
          if (respond_words.equals(from)) {
              respond_list[j] = changeWord[i]; // change words if detected words is exactly the same as respond words
              replaced = true; // set the determinant boolean to true if words are replaced
              }
          }
      }

    if (replaced){
      String result = String.join(" ", respond_list); //join the respond_list into a string
      return result;
    }else{
        Random rand = new Random(); //get a random number for the index of the replies list
        int index = rand.nextInt(replies.length);
        return (replies[index]); //return a random fixed reply
    }
  }

  /**
   * Creates a new object named myConversation, runs the chat method and prints the transcript
   * @param arguments
   */
  public static void main(String[] arguments) {
    List<String> history = new ArrayList<>(); // creates an empty array list for new chat history to store
    String[] fixed_replies = new String[]{"Mmm-hm", "That's great", "Interesting", "I see...", "I want know more about this"}; // creates the list of canned response
    String[] fromwords = new String[]{"I","me", "am", "you", "my", "your", "are", "I'm", "You're"}; // creates the list of key words
    String[] towords   = new String[]{"you","you", "are", "I","your", "my", "is", "You're", "I'm"}; // creates the list of changed words
    Conversation myConversation = new Conversation(history, fixed_replies, fromwords, towords); // creates a new object named myConversation
    myConversation.chat(); // uses the method chat to the object myConversation, performing the functions of chatbox
    myConversation.printTranscript(); // uses the method printTranscript to the object myConversation, making it print out the chat history
  }
}