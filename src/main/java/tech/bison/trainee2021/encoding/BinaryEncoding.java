package tech.bison.trainee2021.encoding;

import java.util.Objects;

public class BinaryEncoding {

  public static final char POSITIVE_BIT = '1';
  public static final char NEGATIVE_BIT = '0';
  private String binaryString;

  public BinaryEncoding(String binaryString) {
    setBinaryString(binaryString);
  }

  public String getBinaryString() {
    return binaryString;
  }

  @Override
  public int hashCode() {
    return Objects.hash(binaryString);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BinaryEncoding other = (BinaryEncoding) obj;
    return Objects.equals(binaryString, other.binaryString);
  }

  @Override
  public String toString() {
    return getBinaryString();
  }

  private void setBinaryString(String binaryString) {
    validateBinaryString(binaryString);
    this.binaryString = binaryString;
  }

  private void validateBinaryString(String binaryString) {
    for (char bit : binaryString.toCharArray()) {
      if (bit != NEGATIVE_BIT && bit != POSITIVE_BIT) {
        throw new IllegalArgumentException(String.format("\"%s\" cannot be used as a binary encoding.", binaryString));
      }
    }
  }
}
