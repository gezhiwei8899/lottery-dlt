package com.gezhiwe.lotterydlt.dao.mapper;

import com.gezhiwe.lotterydlt.dao.entity.LotteryNumThink;

public interface LotteryNumThinkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LotteryNumThink record);

    int insertSelective(LotteryNumThink record);

    LotteryNumThink selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LotteryNumThink record);

    int updateByPrimaryKey(LotteryNumThink record);
}