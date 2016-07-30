package com.ufcg.services;

public interface StatisticService {

    int resolvedProblems();
    int resolvedProblems(Long userId);
    int submittingProblems();
}
