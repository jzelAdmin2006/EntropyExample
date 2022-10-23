package tech.bison.trainee2021.userInterface.command;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class EncodeCommandTest {
  @Test
  void newEncodeCommand_execute_argumentIsEncoded() {
    EncodeCommand encodeCommand = new EncodeCommand();

    String result = encodeCommand.execute("A");

    assertThat(result).isEqualTo("1_{A=1}");
  }

  @Test
  void newEncodeCommand_execute_differentArgumentIsEncoded() {
    EncodeCommand encodeCommand = new EncodeCommand();

    String result = encodeCommand.execute("AB");

    assertThat(result).isEqualTo("01_{A=0, B=1}");
  }

  @Test
  void newLargeEncodeCommand_execute_largeArgumentIsEncoded() {
    EncodeCommand encodeCommand = new EncodeCommand();

    String result = encodeCommand.execute("aaaaaadsvb");

    assertThat(result).isEqualTo("1111110001001010000_{a=1, b=0000, s=001, d=0001, v=01}");
  }
}
