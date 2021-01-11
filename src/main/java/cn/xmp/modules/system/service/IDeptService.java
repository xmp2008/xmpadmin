package cn.xmp.modules.system.service;

import cn.xmp.modules.system.entity.Dept;
import cn.xmp.modules.system.model.request.DeptPageParam;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
public interface IDeptService extends IService<Dept> {
    public BaseResponse add(Dept model);

    public BaseResponse delete(Dept model);

    public BaseResponse update(Dept model);

    public BaseResponse query(Dept model);

    public PageResponse page(DeptPageParam pageParam);
}
