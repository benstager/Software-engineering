
import java.util.ArrayList;
/**
@author Ben Stager
**/
public class Ballot{
	private String officeName;
	private ArrayList<Candidate> candidateList = new ArrayList<Candidate>();

	/**
	@param officeName
	public constructor Ballot takes in the officeName parameter
	**/
	public Ballot(String officeName){
		this.officeName = officeName;
	}
	/**
	@return officeName of type String and creates name of voting office
	method getOfficeName() returns the officeName parameter that is passed through
	the constructor
	**/
	public String getOfficeName(){
    return officeName;
	}

	/**
	@param c is of type Candidate object
	public method addCandidate adds object Candidate to candidateList
	**/
	public void addCandidate(Candidate c){
			if (!this.candidateList.contains(c)){
				candidateList.add(c);
			} else{
				System.out.println("Error: Duplicate candidate not allowed");
			}
	}

	/**
	@return candidateList
	public method ArrayList<Candidate> returns ArrayList that was created
	**/
	public ArrayList<Candidate> getCandidates(){
		return candidateList;
	}
}
