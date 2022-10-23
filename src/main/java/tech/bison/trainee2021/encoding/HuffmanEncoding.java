package tech.bison.trainee2021.encoding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoding {

  public static final String STRINGPARSE_DELIMITER = "_";
  private final String input;
  private final BinaryEncoding encoding;
  private final HashMap<Character, BinaryEncoding> encodingTable;

  public HuffmanEncoding(String input) {
    encodingTable = new HashMap<>();
    this.input = input;
    this.encoding = encode(input);
  }

  public HuffmanEncoding(BinaryEncoding encoding, HashMap<Character, BinaryEncoding> encodingTable) {
    this.encoding = encoding;
    this.encodingTable = encodingTable;
    this.input = decode();
  }

  /*
   * This method doesn't react to incorrectly formatted HuffmanEncodings. Use it at your own risk!
   */
  @Deprecated
  public static HuffmanEncoding parseHuffmanEncoding(String formattedHuffmanEncoding) {
    String huffmanEncodingBinaryString = formattedHuffmanEncoding.split(STRINGPARSE_DELIMITER)[0];
    String[] huffmanEncodingTableRows = formattedHuffmanEncoding
        .substring(huffmanEncodingBinaryString.length() + 2, formattedHuffmanEncoding.length() - 1)
        .split(", ");
    BinaryEncoding completeHuffmanEncoding = new BinaryEncoding(huffmanEncodingBinaryString);
    HashMap<Character, BinaryEncoding> encodingTable = new HashMap<>();
    for (String huffmanEncodingTableRow : huffmanEncodingTableRows) {
      char character = huffmanEncodingTableRow.charAt(0);
      BinaryEncoding encoding = new BinaryEncoding(huffmanEncodingTableRow.substring(2));
      encodingTable.put(character, encoding);
    }
    return new HuffmanEncoding(completeHuffmanEncoding, encodingTable);
  }

  public String getInput() {
    return input;
  }

  public BinaryEncoding getEncoding() {
    return encoding;
  }

  public Map<Character, BinaryEncoding> getEncodingTable() {
    return Collections.unmodifiableMap(encodingTable);
  }

  @Override
  public String toString() {
    return getEncoding() + STRINGPARSE_DELIMITER + getEncodingTable();
  }

  private BinaryEncoding encode(String input) {
    char[] inputChars = input.toCharArray();
    inputChars = sortByFrequency(inputChars);
    for (int inputCharIndex = 0; inputCharIndex < inputChars.length; inputCharIndex++) {
      String binaryString = "";
      for (int zeroBitIndex = 0; zeroBitIndex < inputCharIndex; zeroBitIndex++) {
        binaryString += BinaryEncoding.NEGATIVE_BIT;
      }
      if (inputCharIndex < inputChars.length && (inputCharIndex < inputChars.length - 1 || inputChars.length == 1)) {
        binaryString += BinaryEncoding.POSITIVE_BIT;
      }
      encodingTable.put(inputChars[inputCharIndex], new BinaryEncoding(binaryString));
    }
    String binaryString = "";
    for (int i = 0; i < input.length(); i++) {
      binaryString += encodingTable.get(input.charAt(i)).getBinaryString();
    }
    return new BinaryEncoding(binaryString);
  }

  private char[] sortByFrequency(char[] chars) {
    char[] charsWithoutDuplicates = removeDuplicates(chars);
    HashMap<Character, Integer> frequencies = new HashMap<>();
    for (char character : charsWithoutDuplicates) {
      int counter = 0;
      for (char occuringChar : chars) {
        if (character == occuringChar) {
          counter++;
        }
      }
      frequencies.put(character, counter);
    }
    LinkedHashMap<Character, Integer> sortedFrequencies = sortHashMapByValues(frequencies);

    char[] sortedChars = new char[sortedFrequencies.size()];
    int i = 0;
    for (@SuppressWarnings("rawtypes")
    Map.Entry mapElement : sortedFrequencies.entrySet()) {
      char key = (char) mapElement.getKey();
      sortedChars[sortedFrequencies.size() - i - 1] = key;
      i++;
    }
    return sortedChars;
  }

  private LinkedHashMap<Character, Integer> sortHashMapByValues(HashMap<Character, Integer> mapToSort) {
    List<Character> mapKeys = new ArrayList<>(mapToSort.keySet());
    List<Integer> mapValues = new ArrayList<>(mapToSort.values());
    Collections.sort(mapValues);
    Collections.sort(mapKeys);
    LinkedHashMap<Character, Integer> sortedMap = new LinkedHashMap<>();
    Iterator<Integer> valueIt = mapValues.iterator();
    while (valueIt.hasNext()) {
      Integer val = valueIt.next();
      Iterator<Character> keyIt = mapKeys.iterator();
      while (keyIt.hasNext()) {
        Character key = keyIt.next();
        Integer comp1 = mapToSort.get(key);
        Integer comp2 = val;
        if (comp1.equals(comp2)) {
          keyIt.remove();
          sortedMap.put(key, val);
          break;
        }
      }
    }
    return sortedMap;
  }

  private char[] removeDuplicates(char[] originalCharacters) {
    List<Character> whiteList = new ArrayList<>();
    for (char character : originalCharacters) {
      if (!whiteList.contains(character)) {
        whiteList.add(character);
      }
    }
    char[] staticWhiteList = new char[whiteList.size()];
    for (int i = 0; i < whiteList.size(); i++) {
      staticWhiteList[i] = whiteList.get(i);
    }
    return staticWhiteList;
  }

  private String decode() {
    HashMap<BinaryEncoding, Character> reversedEncodingTable = new HashMap<>();
    for (char character : encodingTable.keySet()) {
      reversedEncodingTable.put(encodingTable.get(character), character);
    }
    List<BinaryEncoding> encodedCharacters = extractEncodedCharacters(reversedEncodingTable);
    String decodedString = "";
    for (BinaryEncoding encodedCharacter : encodedCharacters) {
      decodedString += reversedEncodingTable.get(encodedCharacter);
    }
    return decodedString;
  }

  private List<BinaryEncoding> extractEncodedCharacters(HashMap<BinaryEncoding, Character> reversedEncodingTable) {
    List<BinaryEncoding> encodedCharacters = new ArrayList<>();
    String bits = encoding.getBinaryString();
    while (!bits.equals("")) {
      int i = 0;
      while (i <= bits.length() && reversedEncodingTable.get(new BinaryEncoding(bits.substring(0, i))) == null) {
        i++;
      }
      encodedCharacters.add(new BinaryEncoding(bits.substring(0, i)));
      bits = bits.substring(i);
    }
    return encodedCharacters;
  }
}
