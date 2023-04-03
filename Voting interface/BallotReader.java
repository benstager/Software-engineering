
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.util.Scanner;

public class BallotReader{

	public static Ballot readBallot(String filename)
	throws IOException {
		/**
		@param filename is used to write to the Ballot ballot object that is written
		on result writer
		@return Ballot returns an object of type Ballot that is passed through
		ResultWriter
		**/

		// Declare a file inFile that is used to add candidates to
		File inFile = new File(filename);
		// Create FileInputStream that parses through inFile
		FileInputStream readFile = new FileInputStream(inFile);
		// Declare Scanner that reads lines of files
		Scanner scnr = new Scanner(readFile);

		// The first line of the Ballot is the name of the award i.e. James Beard Award
		String officeName = scnr.nextLine();

		// Add the officeName to the ballot
		Ballot newBallot = new Ballot(officeName);

		// Find the amount of candidates by counting the amount of lines
		int candidates = Integer.parseInt(scnr.nextLine());
		// The for loop is used to add each candidate in a formatted way
		// so that it can be returned to the ballot and formatted in the correctway

		for (int i = 0; i < candidates; ++i){
			String newCandidate = scnr.nextLine();
			//String [] nameAndParty = newCandidate.split(";");
			String name = newCandidate.split(";")[0];
			String party = newCandidate.split(";")[1];
			//System.out.println(nameAndParty[0], nameAndParty[1]);
			newBallot.addCandidate(new Candidate(name, party));
		}

		return newBallot;
	}
}
