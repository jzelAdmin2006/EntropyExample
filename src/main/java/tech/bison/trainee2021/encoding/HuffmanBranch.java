package tech.bison.trainee2021.encoding;

public class HuffmanBranch {

  private final Character character;
  private final double frequency;
  private HuffmanBranch[] connectedHuffmanBranches = { null, null };
  private boolean isVisited = false;
  private HuffmanBranch parentBranch;

  public HuffmanBranch(char character, double frequency) {
    this.character = character;
    this.frequency = frequency;
  }

  public HuffmanBranch(HuffmanBranch firstHuffmanBranch, HuffmanBranch secondHuffmanBranch) {
    firstHuffmanBranch.setParentBranch(this);
    secondHuffmanBranch.setParentBranch(this);
    HuffmanBranch[] connectedHuffmanBranch = { firstHuffmanBranch, secondHuffmanBranch };
    this.connectedHuffmanBranches = connectedHuffmanBranch;
    this.frequency = firstHuffmanBranch.getFrequency() + secondHuffmanBranch.getFrequency();
    character = null;
  }

  public Character getCharacter() {
    return character;
  }

  public double getFrequency() {
    return frequency;
  }

  public HuffmanBranch getFirstConnectedHuffmanBranch() {
    return connectedHuffmanBranches[0];
  }

  public HuffmanBranch getSecondConnectedHuffmanBranch() {
    return connectedHuffmanBranches[1];
  }

  public boolean isVisited() {
    return isVisited;
  }

  public void setVisited() {
    this.isVisited = true;
  }

  public HuffmanBranch getParentBranch() {
    return parentBranch;
  }

  public void setParentBranch(HuffmanBranch parentBranch) {
    this.parentBranch = parentBranch;
  }
}
