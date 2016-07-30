package com.ufcg.services;

/**
 * Created by franklin on 29/07/16.
 */
public interface StatisticService {

    int resolvedProblems();
    int resolvedProblems(Long userId);
    int submittingProblems();
}
