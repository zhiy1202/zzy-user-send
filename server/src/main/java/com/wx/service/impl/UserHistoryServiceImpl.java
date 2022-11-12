package com.wx.service.impl;

import com.wx.entity.UserHistory;
import com.wx.mapper.UserHistoryMapper;
import com.wx.service.IUserHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-08-29
 */
@Service
public class UserHistoryServiceImpl extends ServiceImpl<UserHistoryMapper, UserHistory> implements IUserHistoryService {

}
