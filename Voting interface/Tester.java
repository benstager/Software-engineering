import java.util.*;
import java.io.IOException;
public class Tester{
  public static void main(String[] args){
    Candidate candidate1 = new Candidate("Ben Stager", "Democrat");
    Candidate candidate2 = new Candidate("Jon Doe", "Republican");

    Ballot newBallot = new Ballot("2021 Race");

    newBallot.addCandidate(candidate1);
    newBallot.addCandidate(candidate2);

    System.out.println(newBallot.getOfficeName());
    System.out.println(newBallot.getCandidates());

    //BallotReader.readBallot("input.txt")
    try{
    BallotReader.readBallot("input.txt");
  }
  catch(IOException excpt){
    System.out.println("File not read");
  }
  }
}
