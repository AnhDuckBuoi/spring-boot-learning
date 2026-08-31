package com.devteria.profile.service;

import com.devteria.profile.dto.request.UserProfileCreationRequest;
import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.entity.UserProfile;
import com.devteria.profile.mapper.UserProfileMapper;
import com.devteria.profile.repository.UserProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class UserProfileService {
    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;
    private final MappingContext mappingContext;

    public UserProfileResponse createUserProfile(UserProfileCreationRequest request){
        UserProfile userProfile = userProfileMapper.toUserProfile(request);
        userProfileRepository.save(userProfile);
        return userProfileMapper.toUserProfileResponse(userProfile);
    }
    public UserProfileResponse getUserProfile(String id){
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User profile not found"));
        return userProfileMapper.toUserProfileResponse(userProfile);
    }
}
