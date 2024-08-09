package com.anxstra.cache;

import com.anxstra.entities.User;
import com.anxstra.mappers.UserBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.anxstra.constants.CommonConstants.USER_CACHE_NAME;

@Component
@RequiredArgsConstructor
public class UserCacheUtils {

    private final CacheManager cacheManager;

    public void put(User user) {

        Cache cache = cacheManager.getCache(USER_CACHE_NAME);

        Objects.requireNonNull(cache);
        cache.put(user.getId(), UserBuilder.createUserCacheDto(user));
    }
}
