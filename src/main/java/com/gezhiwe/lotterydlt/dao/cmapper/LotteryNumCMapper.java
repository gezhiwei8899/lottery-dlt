package com.gezhiwe.lotterydlt.dao.cmapper;

import com.gezhiwe.lotterydlt.dao.mapper.LotteryNumMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface LotteryNumCMapper extends LotteryNumMapper {

    Long exsitLotteryNumInSchedule(@Param("standartOpenDay") Date standartOpenDay,
                                   @Param("maxOPenDay") Date maxOPenDay);

    Long selectExsitByNum(@Param("openNum") Long openNum);
}
