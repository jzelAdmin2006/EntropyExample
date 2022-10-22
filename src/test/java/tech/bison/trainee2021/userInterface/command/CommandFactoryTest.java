package tech.bison.trainee2021.userInterface.command;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CommandFactoryTest {
  @Test
  void create_encodeCommand_isEncodeCommand() {
    Command encodeCommand = CommandFactory.create(KnownCommand.ENCODE);

    assertThat(encodeCommand).isInstanceOf(EncodeCommand.class);
  }

  @Test
  void create_decodeCommand_isDecodeCommand() {
    Command decodeCommand = CommandFactory.create(KnownCommand.DECODE);

    assertThat(decodeCommand).isInstanceOf(DecodeCommand.class);
  }

  @Test
  void create_invalidCommand_isCommandNotFoundCommand() {
    Command invalidCommand = CommandFactory.create("asdfqwetewrugqur");

    assertThat(invalidCommand).isInstanceOf(CommandNotFoundCommand.class);
  }
}
