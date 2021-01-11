package cn.xmp.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * Excel导入导出测试
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@Data
@TableName("t_eximport")
public class Eximport extends Model<Eximport> {

    private static final long serialVersionUID = 1L;

    /**
     * 字段1
     */
    @TableField("FIELD1")
    private String field1;
    /**
     * 字段2
     */
    @TableField("FIELD2")
    private Integer field2;
    /**
     * 字段3
     */
    @TableField("FIELD3")
    private String field3;
    @TableField("CREATE_TIME")
    private Date createTime;

}
