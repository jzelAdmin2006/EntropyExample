package tech.bison.trainee2021.encoding;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class BinaryEncodingTest {
  @Test
  void newBinaryEncodingWithBinaryStringOne_getBinaryString_isOne() {
    BinaryEncoding binaryEncoding = new BinaryEncoding("1");

    String result = binaryEncoding.getBinaryString();

    assertThat(result).isEqualTo("1");
  }

  @Test
  void newBinaryEncodingWithBinaryStringOneZero_getBinaryString_isOneZero() {
    BinaryEncoding binaryEncoding = new BinaryEncoding("10");

    String result = binaryEncoding.getBinaryString();

    assertThat(result).isEqualTo("10");
  }

  @Test
  void newBinaryEncodingWithInvalidBinaryString_throwsIllegalArgumentException_hasCorrectMessage() {
    assertThatThrownBy(() -> new BinaryEncoding("20")).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("\"20\" cannot be used as a binary encoding.");
  }

  @Test
  void newBinaryEncodingWithBinaryStringOne_toString_isOne() {
    BinaryEncoding binaryEncoding = new BinaryEncoding("1");

    String result = binaryEncoding.toString();

    assertThat(result).isEqualTo("1");
  }
}
