package tech.bison.trainee2021.userInterface.command;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class KnownCommandTest {
  @Test
  void encode_translate_staysTheSame() {
    String command = "/encode";

    String result = KnownCommand.translate(command);

    assertThat(result).isEqualTo("/encode");
  }

  @Test
  void decode_translate_staysTheSame() {
    String command = "/encode";

    String result = KnownCommand.translate(command);

    assertThat(result).isEqualTo("/encode");
  }

  @Test
  void invalidCommand_translate_becomesNotFound() {
    String command = "/asdf";

    String result = KnownCommand.translate(command);

    assertThat(result).isEqualTo("");
  }
}
