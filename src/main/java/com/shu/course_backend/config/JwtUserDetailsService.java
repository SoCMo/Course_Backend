package com.shu.course_backend.config;

import com.shu.course_backend.dao.PasswordDoMapper;
import com.shu.course_backend.dao.UserDoMapper;
import com.shu.course_backend.model.UserRole;
import com.shu.course_backend.model.entity.PasswordDo;
import com.shu.course_backend.model.entity.UserDo;
import com.shu.course_backend.model.entity.UserDoExample;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {


    @Resource
    private UserDoMapper userDOMapper;

    @Resource
    private PasswordDoMapper passwordDoMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中获取user的详细信息
        UserDoExample userDOExample = new UserDoExample();

        userDOExample
                .createCriteria()
                .andUserIdEqualTo(username);
        List<UserDo> userDOList = userDOMapper.selectByExample(userDOExample);
        if (userDOList.size() == 1) {
            UserDo userDO = userDOList.get(0);
            PasswordDo passwordDo = passwordDoMapper.selectByPrimaryKey(userDO.getUserId());
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(UserRole.getUserRole(userDO.getIdentity())));
            return new User(userDO.getUserId(), passwordDo.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("Not found with username: " + username);
        }
    }
}
