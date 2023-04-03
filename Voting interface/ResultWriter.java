

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
public class ResultWriter{

	public static void writeResults(String filename, Ballot ballot)
	throws IOException {
			/**
			@param filename takes in file to be written on
			@param ballot receives ballot object of class Ballot
			@return void
			The method takes in a file to be written on. The ballot is written onto the file using
			PrintWriter filePrint and using various methods to compare length of candidate strings
			and vote counts
			**/

			// Declare a printer of type PrintWriter that takes in a filename
			PrintWriter filePrint = new PrintWriter(new FileWriter(filename));

			// The first line of the ballot is the name of the award

			String firstLine = "RESULTS - " + ballot.getOfficeName();
			filePrint.println(firstLine);

			// FIXME: figure out how to print correct amount of dashes
			for (int i = 0; i < firstLine.length(); i++){
				filePrint.print("-");
			}
			// Declare an ArrayList of object type Candidate that is populted with all of the candidates
			// this is what will be iterated through when comparing candidates
			ArrayList<Candidate> candidates = ballot.getCandidates();
			// Begin by initizializing the winner as the first person
			// in the candidates list, this way it will be compared to each candidate
			// in the list
			int winnerVotes = candidates.get(0).getVoteCount();
			Candidate winner = new Candidate("empty","empty");
			// This var of type boolean will be implemented later to determine if there
			// is a clear winner in the election
		  boolean winOrNo = false;
			// Now create a count variable that will become the candidate with the
			// the first candidate is initialized as the first candidate in the list
			// with the voteCount of the first candidate
			int mostWordsOfCandidate = 0;
			// int firstCandidate = candidate.get(0).getVoteCount;

			// iterate through each Candidate to find the one with the most characters
			// if none of them are more than firstCandidate, then first candidate heads
			// the ballot
			int firstCandidate = candidates.get(0).toString().length();
			for (int i = 0; i < candidates.size(); ++i){
				int nextCandidate = candidates.get(i).toString().length();
				if (nextCandidate > firstCandidate){
					mostWordsOfCandidate = nextCandidate;
				}
				else{
					mostWordsOfCandidate = firstCandidate;
				}
			}

			// This for loop iterates through each voteCount and determines if there
			// is a winner or not. The boolean winOrNo is held as true if there is
			// tiebreaker in the election. The loop iterates through the entire
			// ArrayList candidates. If no votes are more than candidate 0, then candidate
			// 0 is the winner
			for (int i = 0; i < candidates.size(); ++i){
				int spacesCandidate = (mostWordsOfCandidate - candidates.get(i).toString().length()) + 12;
				String candidatePerSpace = candidates.get(i).toString();
				candidatePerSpace += spacesCandidate;
				filePrint.println(candidatePerSpace);
			}

			for (int i = 0; i < candidates.size(); ++i){
				int candidateVotes = candidates.get(i).getVoteCount();

				if (candidateVotes > winnerVotes){
					winnerVotes = candidateVotes;
					winner = candidates.get(i);
					//winner = candidates.get(i);
					winOrNo = false;
				}else if(candidateVotes == winnerVotes){
					winOrNo = true;
				}
			}

			// The final statement here passes through the winOrNo variable,
			// if there is no winner the variable is stored as false and "NO WINNER"
			// is printed to the file
			if (winOrNo == true){
				filePrint.println("WINNER:" + winner.toString());
			}
			else{
				filePrint.println("NO WINNER");
			}

	}
}
