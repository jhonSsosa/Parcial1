package edu.eci.cvds.tdd.registry;

import java.util.HashSet;

public class Registry {

    private HashSet<Integer> IDVotes;

    /**
     * Constructor method for the Registry class. Initializes the HashSet to store voter IDs.
     */
    public Registry() {
        IDVotes = new HashSet<>();
    }

    /**
     * Registers a voter based on the provided Person object.
     * 
     * @param p The Person object representing the voter.
     * @return A RegisterResult indicating the result of the registration process.
     */
    public RegisterResult registerVoter(Person p) {
        // Check if the person has voted before
        if (hasVotedBefore(p.getId())) {
            return RegisterResult.DUPLICATED;
        }

        // Check if the person is alive
        if (!p.isAlive()) {
            return RegisterResult.DEAD;
        }

        // Check if the person is underage
        if (p.getAge() < 18 && p.getAge() >= 0) {
            return RegisterResult.UNDERAGE;
        }

        // Check if the age of the person is invalid
        if (p.getAge() < 0 || p.getAge() >= 135) {
            return RegisterResult.INVALID_AGE;
        }

        // Add the person's ID to the set of voted IDs
        IDVotes.add(p.getId());

        // Return that the registration was valid
        return RegisterResult.VALID;
    }

    /**
     * Checks if a person with the given ID has voted before.
     * 
     * @param ID The ID of the person to check.
     * @return true if the person has voted before, false otherwise.
     */
    private boolean hasVotedBefore(Integer ID) {
        return IDVotes.contains(ID);
    } 
}
