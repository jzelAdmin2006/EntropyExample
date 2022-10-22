package tech.bison.trainee2021.encoding;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class HuffmanEncodingTest {
  @Test
  void newHuffmanEncodingWithInputLoremIpsum_getInput_isLoremIpsum() {
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding("Lorem Ipsum");

    String result = huffmanEncoding.getInput();

    assertThat(result).isEqualTo("Lorem Ipsum");
  }

  @Test
  void newHuffmanEncodingWithInputLoremIpsum2_getInput_isLoremIpsum2() {
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding("Lorem Ipsum 2");

    String result = huffmanEncoding.getInput();

    assertThat(result).isEqualTo("Lorem Ipsum 2");
  }

  @Test
  void newHuffmanEncodingWithEncodingOne_getEncoding_isOne() {
    HashMap<Character, BinaryEncoding> encodingTable = new HashMap<>();
    encodingTable.put('A', new BinaryEncoding("1"));
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding(new BinaryEncoding("1"), encodingTable);

    BinaryEncoding result = huffmanEncoding.getEncoding();

    assertThat(result).isEqualTo(new BinaryEncoding("1"));
  }

  @Test
  void newHuffmanEncodingWithEncodingOneZero_getEncoding_isOneZero() {
    HashMap<Character, BinaryEncoding> encodingTable = new HashMap<>();
    encodingTable.put('A', new BinaryEncoding("10"));
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding(new BinaryEncoding("10"), encodingTable);

    BinaryEncoding result = huffmanEncoding.getEncoding();

    assertThat(result).isEqualTo(new BinaryEncoding("10"));
  }

  @Test
  void newHuffmanEncodingWithInputA_getEncoding_isOne() {
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding("A");

    BinaryEncoding result = huffmanEncoding.getEncoding();

    assertThat(result).isEqualTo(new BinaryEncoding("1"));
  }

  @Test
  void newHuffmanEncodingWithInputAB_getEncoding_isZeroOneOne() {
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding("AB");

    BinaryEncoding result = huffmanEncoding.getEncoding();

    assertThat(result).isEqualTo(new BinaryEncoding("011"));
  }

  @Test
  void newHuffmanEncodingWithEncodingTableAEquealsOne_getEncodingTable_isAEquealsOne() {
    HashMap<Character, BinaryEncoding> encodingTable = new HashMap<>();
    encodingTable.put('A', new BinaryEncoding("1"));
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding(new BinaryEncoding("1"), encodingTable);

    Map<Character, BinaryEncoding> result = huffmanEncoding.getEncodingTable();

    assertThat(result).isEqualTo(encodingTable);
  }

  @Test
  void newHuffmanEncodingWithEncodingTableBEquealsOne_getEncodingTable_isBEquealsOne() {
    HashMap<Character, BinaryEncoding> encodingTable = new HashMap<>();
    encodingTable.put('B', new BinaryEncoding("1"));
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding(new BinaryEncoding("1"), encodingTable);

    Map<Character, BinaryEncoding> result = huffmanEncoding.getEncodingTable();

    assertThat(result).isEqualTo(encodingTable);
  }

  @Test
  void newHuffmanEncodingWithBinaryEncodingAndTable_getInput_isCorrect() {
    HashMap<Character, BinaryEncoding> encodingTable = new HashMap<>();
    encodingTable.put('B', new BinaryEncoding("1"));
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding(new BinaryEncoding("1"), encodingTable);

    String result = huffmanEncoding.getInput();

    assertThat(result).isEqualTo("B");
  }

  @Test
  void newHuffmanEncodingWithDifferentBinaryEncodingAndTable_getInput_isCorrect() {
    HashMap<Character, BinaryEncoding> encodingTable = new HashMap<>();
    encodingTable.put('B', new BinaryEncoding("1"));
    encodingTable.put('C', new BinaryEncoding("01"));
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding(new BinaryEncoding("101"), encodingTable);

    String result = huffmanEncoding.getInput();

    assertThat(result).isEqualTo("BC");
  }

  @Test
  void newHuffmanEncodingIncludingBinaryEncodedCharactersWithZerosOnly_getInput_isCorrect() {
    HashMap<Character, BinaryEncoding> encodingTable = new HashMap<>();
    encodingTable.put('B', new BinaryEncoding("1"));
    encodingTable.put('C', new BinaryEncoding("01"));
    encodingTable.put('D', new BinaryEncoding("00"));
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding(new BinaryEncoding("10001"), encodingTable);

    String result = huffmanEncoding.getInput();

    assertThat(result).isEqualTo("BDC");
  }

  @Test
  void newHuffmanEncodingWithRandomLargeBinaryEncodingAndTable_getInput_isCorrect() {
    HashMap<Character, BinaryEncoding> encodingTable = new HashMap<>();
    encodingTable.put('_', new BinaryEncoding("1"));
    encodingTable.put('a', new BinaryEncoding("01"));
    encodingTable.put('G', new BinaryEncoding("001"));
    encodingTable.put('0', new BinaryEncoding("0001"));
    encodingTable.put('w', new BinaryEncoding("00001"));
    encodingTable.put('-', new BinaryEncoding("000001"));
    encodingTable.put('B', new BinaryEncoding("0000001"));
    encodingTable.put('v', new BinaryEncoding("00000001"));
    encodingTable.put('$', new BinaryEncoding("000000001"));
    encodingTable.put('+', new BinaryEncoding("0000000001"));
    encodingTable.put('C', new BinaryEncoding("00000000001"));
    encodingTable.put('6', new BinaryEncoding("00000000000"));
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding(
        new BinaryEncoding(
            "000000000000001000000000000000000000001001100001000001000000000100000000001000000001000000010000001"),
        encodingTable);

    String result = huffmanEncoding.getInput();

    assertThat(result).isEqualTo("6066aG_w-+C$vB");
  }

  @Test
  void getEncodingTableFromHuffmanEncoding_modify_isUnmodifiable() {
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding("asdf");

    Map<Character, BinaryEncoding> encodingtable = huffmanEncoding.getEncodingTable();

    assertThatThrownBy(() -> encodingtable.put('A', new BinaryEncoding("1")))
        .isInstanceOf(UnsupportedOperationException.class);
    assertThatThrownBy(() -> encodingtable.clear()).isInstanceOf(UnsupportedOperationException.class);
    assertThatThrownBy(() -> encodingtable.putAll(encodingtable)).isInstanceOf(UnsupportedOperationException.class);
    assertThatThrownBy(() -> encodingtable.remove('A')).isInstanceOf(UnsupportedOperationException.class);
    assertThatThrownBy(() -> encodingtable.replace('A', new BinaryEncoding("1")))
        .isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  void huffmannEncodingExists_toString_isCorrect() {
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding("asdf");

    String result = huffmanEncoding.toString();

    assertThat(result).isEqualTo("0001100101_{a=0001, s=1, d=001, f=01}");
  }

  @Test
  void differentHuffmannEncodingExists_toString_isCorrect() {
    HuffmanEncoding huffmanEncoding = new HuffmanEncoding("asdfqwert");

    String result = huffmanEncoding.toString();

    assertThat(result).isEqualTo(
        "000000001001000000010000010000110000001000101_{q=00001, a=000000001, r=0001, s=001, t=01, d=00000001, e=0000001, f=000001, w=1}");
  }

  @Test
  void stringWithCorrectlyFormattedHuffmanEncoding_parseHuffmanEncoding_isCorrect() {
    String correctlyFormattedHuffmanEncoding = "1000101001_{a=1, s=0001, d=01, f=001}";

    @SuppressWarnings("deprecation") // testing purpose
    HuffmanEncoding result = HuffmanEncoding.parseHuffmanEncoding(correctlyFormattedHuffmanEncoding);

    assertThat(result.getEncoding().getBinaryString()).isEqualTo("1000101001");
    assertThat(result.getEncodingTable().toString()).isEqualTo("{a=1, s=0001, d=01, f=001}");
  }
}
