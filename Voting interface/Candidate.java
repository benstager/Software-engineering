
public class Candidate{
	private String name;
	private String affiliation;
	private int voteCount = 0;

	/**
	@param name
	@param affiliation
	public constructor Candidate initiliazes object with parameters name and getAffiliation
	**/
	public Candidate(String name, String affiliation){
		this.name = name;
		this.affiliation = affiliation;
	}

	/**
	public method getName() returns parameter name
	@return name
	**/
	public String getName(){
    return name;
	}

	/**
	public method getAffiliation returns parameter affiliation
	@return affiliation
	**/
	public String getAffiliation(){
    return affiliation;

	}

	/**
	public method getVoteCount returns parm voteCount
	@return voteCount
	**/
	public int getVoteCount(){
    return voteCount;
	}

	/**
	public method tallyVote() adds integer value 1 to voteCount each time it is
	called
	**/
	public void tallyVote(){
		voteCount++;
	}

	/**
	public method toString() serves as print method for object Candidate
	**/
	public String toString(){
    return ("Candidate " + name + " of the " + affiliation + ".");
	}
}
