package catsvsdogs;

import java.util.Scanner;

/**
 *
 * @author Dan Harrison
 */
public class catsvsdogs {

    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int totalSets = input.nextInt();
        if (totalSets != 0) {
            input.nextLine();
            for(int set = 1; set <= totalSets; set++) {
                input.nextInt();
                input.nextInt();
                int numVoters = input.nextInt();
                Vote.populateConflictMap(numVoters);
                input.nextLine();
                for(Integer line = 1; line <= numVoters; line++) {
                    String voteLine = input.nextLine();
                    Scanner readVotes = new Scanner(voteLine);
                    while(readVotes.hasNext()) {
                        String keepVote = readVotes.next();
                        String kickVote = readVotes.next();
                        new Vote(line, keepVote, kickVote);
                    }
                    readVotes.close();
                }
                Vote.compareVotes();
                Vote.checkConflicts();
                System.out.println(Vote.getMapSize());
                Vote.clearMaps();
                
            }
        input.close();
        }
    }    
}
