package com.ufcg.services;

import org.springframework.stereotype.Service;

@Service("statisticService")
public class StatisticServiceImpl implements StatisticService {
    @Override
    public int resolvedProblems() {
        return 0;
    }

    @Override
    public int resolvedProblems(Long userId) {
        return 0;
    }

    @Override
    public int submittingProblems() {
        return 0;
    }
}
