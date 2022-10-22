package tech.bison.trainee2021.userInterface;

public class CommandNotFoundCommand implements Command {

  @Override
  public String execute(String value) {
    return "Invalid command!";
  }

}
