package tech.bison.trainee2021.userInterface;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class UserInterfaceTest {
  @Test
  void newUserInterface_generateOutputExit_outputIsCorrect() {
    UserInterface userInterface = new UserInterface();

    String result = userInterface.generateOutput("/exit");

    assertThat(result).isEqualTo("Terminating...");
  }

  @Test
  void newUserInterface_generateOutputEncode() {
    UserInterface userInterface = new UserInterface();

    String result = userInterface.generateOutput("/encode a");

    assertThat(result).isEqualTo("1_{a=1}");
  }

  @Test
  void newUserInterface_generateOutputDecode() {
    UserInterface userInterface = new UserInterface();

    String result = userInterface.generateOutput("/decode 1_{a=1}");

    assertThat(result).isEqualTo("a");
  }
}
