package cn.xmp.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户权限测试
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@TableName("t_data_permission_test")
public class DataPermissionTest extends Model<DataPermissionTest> {

    private static final long serialVersionUID = 1L;

    @TableField("FIELD1")
    private String field1;
    @TableField("FIELD2")
    private String field2;
    @TableField("FIELD3")
    private String field3;
    @TableField("FIELD4")
    private String field4;
    @TableField("DEPT_ID")
    private Integer deptId;
    @TableField("CREATE_TIME")
    private Date createTime;
    @TableId("ID")
    private Integer id;


    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "DataPermissionTest{" +
        "field1=" + field1 +
        ", field2=" + field2 +
        ", field3=" + field3 +
        ", field4=" + field4 +
        ", deptId=" + deptId +
        ", createTime=" + createTime +
        ", id=" + id +
        "}";
    }
}
