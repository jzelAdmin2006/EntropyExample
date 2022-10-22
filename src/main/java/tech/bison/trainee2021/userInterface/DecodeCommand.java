package tech.bison.trainee2021.userInterface;

import tech.bison.trainee2021.encoding.HuffmanEncoding;

public class DecodeCommand implements Command {

  @SuppressWarnings("deprecation") // assuming the user formats the input correctly via copying it
  @Override
  public String execute(String commandArgument) {
    return HuffmanEncoding.parseHuffmanEncoding(commandArgument).getInput();
  }
}
