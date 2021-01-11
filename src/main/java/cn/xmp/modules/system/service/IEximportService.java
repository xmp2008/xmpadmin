package cn.xmp.modules.system.service;

import cn.xmp.modules.system.entity.Eximport;
import cn.xmp.modules.system.model.request.EximportPageParam;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * Excel导入导出测试 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
public interface IEximportService extends IService<Eximport> {
    public BaseResponse add(Eximport model);

    public BaseResponse delete(Eximport model);

    public BaseResponse update(Eximport model);

    public BaseResponse query(Eximport model);

    public PageResponse page(EximportPageParam pageParam);
}
