package cn.xmp.modules.system.service;

import cn.xmp.modules.system.entity.Job;
import cn.xmp.modules.system.model.request.JobPageParam;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 定时任务表 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
public interface IJobService extends IService<Job> {
    public BaseResponse add(Job model);

    public BaseResponse delete(Job model);

    public BaseResponse update(Job model);

    public BaseResponse query(Job model);

    public PageResponse page(JobPageParam pageParam);
}
