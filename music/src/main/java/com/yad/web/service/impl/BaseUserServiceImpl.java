package com.yad.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yad.web.entity.BaseUser;
import com.yad.web.entity.vo.UserLoginVo;
import com.yad.web.mapper.BaseUserMapper;
import com.yad.web.service.BaseUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yad.web.utils.MD5;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yad
 * @since 2020-12-25
 */
@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements BaseUserService {
    @Override
    public BaseUser login(UserLoginVo user) {
        QueryWrapper<BaseUser> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_no",user.getStuNo());
        BaseUser one = this.getOne(wrapper);
        if (one==null){
            return  null;
        }
        String encrypt = MD5.encrypt(user.getPassword());
        return  one.getPassword().equals(encrypt) ? one : null;
    }

    @Override
    public boolean register(BaseUser user) {
        try {
            boolean save = this.save(user);
            return  save;
        }catch (DuplicateKeyException e){
            return  false;
        }
    }

    @Override
    public BaseUser restLogin(BaseUser baseUser) {
        QueryWrapper<BaseUser> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_no",baseUser.getStuNo());
        BaseUser one = this.getOne(wrapper);
        if (one==null){
            return  null;
        }
        String encrypt = baseUser.getPassword();
        return  one.getPassword().equals(encrypt) ? one : null;
    }
}
