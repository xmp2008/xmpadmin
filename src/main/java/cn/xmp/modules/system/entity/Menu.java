package cn.xmp.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@TableName("t_menu")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单/按钮ID
     */
    @TableId(value = "MENU_ID", type = IdType.AUTO)
    private Long menuId;
    /**
     * 上级菜单ID
     */
    @TableField("PARENT_ID")
    private Long parentId;
    /**
     * 菜单/按钮名称
     */
    @TableField("MENU_NAME")
    private String menuName;
    /**
     * 菜单URL
     */
    @TableField("URL")
    private String url;
    /**
     * 权限标识
     */
    @TableField("PERMS")
    private String perms;
    /**
     * 图标
     */
    @TableField("ICON")
    private String icon;
    /**
     * 类型 0菜单 1按钮
     */
    @TableField("TYPE")
    private String type;
    /**
     * 排序
     */
    @TableField("ORDER_NUM")
    private Long orderNum;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("MODIFY_TIME")
    private Date modifyTime;


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

    @Override
    public String toString() {
        return "Menu{" +
        "menuId=" + menuId +
        ", parentId=" + parentId +
        ", menuName=" + menuName +
        ", url=" + url +
        ", perms=" + perms +
        ", icon=" + icon +
        ", type=" + type +
        ", orderNum=" + orderNum +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
