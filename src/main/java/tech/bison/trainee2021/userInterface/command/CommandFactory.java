package tech.bison.trainee2021.userInterface.command;

public class CommandFactory {

  public static Command create(String command) {
    command = KnownCommand.translate(command);
    switch (command) {
      case KnownCommand.ENCODE:
        return new EncodeCommand();
      case KnownCommand.DECODE:
        return new DecodeCommand();
      case KnownCommand.NOT_FOUND:
        return new CommandNotFoundCommand();
      default:
        // should never happen
        throw new UnsupportedOperationException("The command " + command + " isn't implemented.");
    }
  }
}
