package tech.bison.trainee2021.userInterface.command;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DecodeCommandTest {
  @Test
  void newDecodeCommand_execute_argumentIsDecoded() {
    DecodeCommand decodeCommand = new DecodeCommand();

    String result = decodeCommand.execute("1_{A=1}");

    assertThat(result).isEqualTo("A");
  }

  @Test
  void newDecodeCommand_execute_differentArgumentIsDecoded() {
    DecodeCommand decodeCommand = new DecodeCommand();

    String result = decodeCommand.execute("101_{A=1, B=01}");

    assertThat(result).isEqualTo("AB");
  }

  @Test
  void newLargeDecodeCommand_execute_largeArgumentIsDecoded() {
    DecodeCommand decodeCommand = new DecodeCommand();

    String result = decodeCommand.execute("11111100100010000101_{a=1, b=01, s=0001, d=001, v=00001}");

    assertThat(result).isEqualTo("aaaaaadsvb");
  }
}
