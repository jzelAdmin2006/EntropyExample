package tech.bison.trainee2021.userInterface.command;

import tech.bison.trainee2021.encoding.HuffmanEncoding;

public class EncodeCommand implements Command {

  @Override
  public String execute(String commandArgument) {
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding(commandArgument);
    return huffmanEncoding.toString();
  }
}
