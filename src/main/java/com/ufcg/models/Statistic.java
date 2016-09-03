package com.ufcg.models;

public class Statistic {

    private int resolvedProblems;
    private int usersSubmittingProblems;
    private int userResolvedProblems;

    public Statistic() {}

    public Statistic(int resolvedProblems, int usersSubmittingProblems) {
        this.resolvedProblems = resolvedProblems;
        this.usersSubmittingProblems = usersSubmittingProblems;
        this.userResolvedProblems = 0;
    }

    public int getResolvedProblems() {
        return resolvedProblems;
    }

    public void setResolvedProblems(int resolvedProblems) {
        this.resolvedProblems = resolvedProblems;
    }

    public int getUsersSubmittingProblems() {
        return usersSubmittingProblems;
    }

    public void setUsersSubmittingProblems(int usersSubmittingProblems) {
        this.usersSubmittingProblems = usersSubmittingProblems;
    }

    public int getUserResolvedProblems() {
        return userResolvedProblems;
    }

    public void setUserResolvedProblems(int userResolvedProblems) {
        this.userResolvedProblems = userResolvedProblems;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "resolvedProblems=" + resolvedProblems +
                ", usersSubmittingProblems=" + usersSubmittingProblems +
                ", userResolvedProblems=" + userResolvedProblems +
                '}';
    }

}
