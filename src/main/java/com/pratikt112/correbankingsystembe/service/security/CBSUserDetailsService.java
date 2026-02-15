package com.pratikt112.correbankingsystembe.service.security;

import com.pratikt112.correbankingsystembe.exception.UserNotFoundException;
import com.pratikt112.correbankingsystembe.model.telm.Telm;
import com.pratikt112.correbankingsystembe.model.telm.TelmId;
import com.pratikt112.correbankingsystembe.repo.TelmRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CBSUserDetailsService implements UserDetailsService {

    private final TelmRepo telmRepo;

    public CBSUserDetailsService(TelmRepo telmRepo) {
        this.telmRepo = telmRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String tellerNo) throws UsernameNotFoundException {
        Telm user = telmRepo.findById(new TelmId("003", tellerNo)).orElseThrow(()-> new UserNotFoundException(tellerNo));

    }


}
