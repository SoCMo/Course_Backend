package com.shu.course_backend.config;

import com.pongshy.dao.UserDOMapper;
import com.pongshy.model.entity.UserDO;
import com.pongshy.model.entity.UserDOExample;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: JwtUserDetailsService
 * @Description: Getting the user details from the database
 *               when authenticating the user details provided by the user
 * @Author: pongshy
 * @Date: 2021/1/8-10:39
 * @Version: V1.0
 **/
@Service
public class JwtUserDetailsService implements UserDetailsService {


    @Resource
    private UserDOMapper userDOMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中获取user的详细信息
        UserDOExample userDOExample = new UserDOExample();

        userDOExample
                .createCriteria()
                .andUsernameEqualTo(username);
        List<UserDO> userDOList = userDOMapper.selectByExample(userDOExample);
        if (userDOList.size() == 1) {
            UserDO userDO = userDOList.get(0);
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(userDO.getRole()));
            return new User(userDO.getUsername(), userDO.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("Not found with username: " + username);
        }
    }
}
