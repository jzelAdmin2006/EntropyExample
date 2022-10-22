package tech.bison.trainee2021.userInterface;

public interface Command {
  /**
   * Execute the command and returns the generated message.
   *
   * @param value
   *          to execute with the command
   * @return message
   */
  String execute(String value);
}
