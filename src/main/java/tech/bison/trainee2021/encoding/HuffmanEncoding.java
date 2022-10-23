package tech.bison.trainee2021.encoding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoding {

  public static final String STRINGPARSE_DELIMITER = "_";
  private final String input;
  private final BinaryEncoding encoding;
  private final HashMap<Character, BinaryEncoding> encodingTable;
  private HuffmanBranch huffmanTree;

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
    if (input.length() == 1) {
      BinaryEncoding binaryEncoding = new BinaryEncoding(String.valueOf(BinaryEncoding.POSITIVE_BIT));
      encodingTable.put(input.charAt(0), binaryEncoding);
      return binaryEncoding;
    } else {
      char[] inputChars = input.toCharArray();
      HashMap<Character, Integer> charFrequencies = getCharFrequencies(inputChars);
      List<HuffmanBranch> topBranches = new ArrayList<>();
      char[] occuringCharsInInput = removeDuplicates(inputChars);
      for (char inputChar : occuringCharsInInput) {
        topBranches.add(new HuffmanBranch(inputChar, charFrequencies.get(inputChar)));
      }
      huffmanTree = generateHuffmanTree(topBranches);
      HashMap<Character, String> charCodes = extractHuffmanTreeCharCodes();
      String binaryString = "";
      for (char inputChar : inputChars) {
        binaryString += charCodes.get(inputChar);
        encodingTable.put(inputChar, new BinaryEncoding(charCodes.get(inputChar)));
      }
      return new BinaryEncoding(binaryString);
    }
  }

  private HashMap<Character, String> extractHuffmanTreeCharCodes() {
    HashMap<Character, String> charCodes = new HashMap<>();
    while (!huffmanTree.isVisited()) {
      boolean charWasFound = false;
      HuffmanBranch workingBranch = huffmanTree;
      String currentCharCode = "";
      while (!charWasFound) {
        if (workingBranch.getCharacter() != null) {
          charWasFound = true;
          charCodes.put(workingBranch.getCharacter(), currentCharCode);
          setVisitedFlagForChar(currentCharCode);
        } else if (!workingBranch.getFirstConnectedHuffmanBranch().isVisited()) {
          currentCharCode += BinaryEncoding.NEGATIVE_BIT;
          workingBranch = workingBranch.getFirstConnectedHuffmanBranch();
        } else if (!workingBranch.getSecondConnectedHuffmanBranch().isVisited()) {
          currentCharCode += BinaryEncoding.POSITIVE_BIT;
          workingBranch = workingBranch.getSecondConnectedHuffmanBranch();
        } else if (!workingBranch.isVisited()) {
          workingBranch.setVisited();
          workingBranch = huffmanTree;
          for (int i = 0; i < currentCharCode.length() - 1; i++) {
            if (currentCharCode.charAt(i) == BinaryEncoding.NEGATIVE_BIT) {
              workingBranch = workingBranch.getFirstConnectedHuffmanBranch();
            } else {
              workingBranch = workingBranch.getSecondConnectedHuffmanBranch();
            }
          }
        } else {
          charWasFound = true;
        }
      }
    }
    return charCodes;
  }

  private void setVisitedFlagForChar(String branchCode) {
    HuffmanBranch workingBranch = huffmanTree;
    for (int i = 0; i < branchCode.length(); i++) {
      if (branchCode.charAt(i) == BinaryEncoding.NEGATIVE_BIT) {
        if (workingBranch.getFirstConnectedHuffmanBranch() != null) {
          workingBranch = workingBranch.getFirstConnectedHuffmanBranch();
        }
      } else {
        if (workingBranch.getSecondConnectedHuffmanBranch() != null) {
          workingBranch = workingBranch.getSecondConnectedHuffmanBranch();
        }
      }
    }
    workingBranch.setVisited();
    for (int i = 0; i < branchCode.length(); i++) {
      if (workingBranch.getParentBranch() != null) {
        workingBranch = workingBranch.getParentBranch();
      }
    }
    huffmanTree = workingBranch;
  }

  private HuffmanBranch generateHuffmanTree(List<HuffmanBranch> treeBuild) {
    while (treeBuild.size() > 1) {
      treeBuild = sortByFrequency(treeBuild);
      HuffmanBranch frontBranch = treeBuild.get(0);
      HuffmanBranch secondFrontBranch = treeBuild.get(1);
      HuffmanBranch connectedHuffmanBranch = new HuffmanBranch(frontBranch, secondFrontBranch);
      treeBuild.remove(1);
      treeBuild.remove(0);
      treeBuild.add(connectedHuffmanBranch);
    }
    return treeBuild.get(0);
  }

  private List<HuffmanBranch> sortByFrequency(List<HuffmanBranch> treeBuild) {
    boolean isSorted = false;
    while (!isSorted) {
      isSorted = true;
      for (int i = 0; i < treeBuild.size() - 1; i++) {
        HuffmanBranch currentBranch = treeBuild.get(i);
        HuffmanBranch nextBranch = treeBuild.get(i + 1);
        if (currentBranch.getFrequency() > nextBranch.getFrequency()) {
          treeBuild.set(i, nextBranch);
          treeBuild.set(i + 1, currentBranch);
          isSorted = false;
        }
      }
    }
    return treeBuild;
  }

  private HashMap<Character, Integer> getCharFrequencies(char[] inputChars) {
    char[] charsWithoutDuplicates = removeDuplicates(inputChars);
    HashMap<Character, Integer> frequencies = new HashMap<>();
    for (char character : charsWithoutDuplicates) {
      int counter = 0;
      for (char occuringChar : inputChars) {
        if (character == occuringChar) {
          counter++;
        }
      }
      frequencies.put(character, counter);
    }
    return frequencies;
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
