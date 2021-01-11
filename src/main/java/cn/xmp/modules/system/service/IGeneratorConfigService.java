package cn.xmp.modules.system.service;

import cn.xmp.modules.system.entity.GeneratorConfig;
import cn.xmp.modules.system.model.request.GeneratorConfigPageParam;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 代码生成配置表 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
public interface IGeneratorConfigService extends IService<GeneratorConfig> {
    public BaseResponse add(GeneratorConfig model);

    public BaseResponse delete(GeneratorConfig model);

    public BaseResponse update(GeneratorConfig model);

    public BaseResponse query(GeneratorConfig model);

    public PageResponse page(GeneratorConfigPageParam pageParam);
}
