package tech.bison.trainee2021.userInterface.command;

public abstract class KnownCommand {
  public static final String NOT_FOUND = "";
  public static final String ENCODE = "/encode";
  public static final String DECODE = "/decode";

  public static String translate(String spellingOfCommand) {
    switch (spellingOfCommand) {
      case ENCODE:
        return spellingOfCommand;
      case DECODE:
        return spellingOfCommand;
      default:
        return NOT_FOUND;
    }
  }
}
