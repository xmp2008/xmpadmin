package cn.xmp.modules.system.service;

import cn.xmp.modules.system.entity.JobLog;
import cn.xmp.modules.system.model.request.JobLogPageParam;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 调度日志表 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
public interface IJobLogService extends IService<JobLog> {
    public BaseResponse add(JobLog model);

    public BaseResponse delete(JobLog model);

    public BaseResponse update(JobLog model);

    public BaseResponse query(JobLog model);

    public PageResponse page(JobLogPageParam pageParam);
}
