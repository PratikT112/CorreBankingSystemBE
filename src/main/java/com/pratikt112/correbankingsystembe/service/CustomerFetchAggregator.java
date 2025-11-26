package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.repo.CmobRepo;
import com.pratikt112.correbankingsystembe.repo.Cr60Repo;
import com.pratikt112.correbankingsystembe.repo.CuidRepo;
import com.pratikt112.correbankingsystembe.repo.CusmRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerFetchAggregator {

    private final CusmRepo cusmRepo;
    private final Cr60Repo cr60Repo;
    private final CmobRepo cmobRepo;
    private final CuidRepo cuidRepo;


}
