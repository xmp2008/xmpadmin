package cn.xmp.modules.common.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * <p>
 *
 * @author xiemopeng
 * @since 2020/11/18
 */
//@MappedSuperclass
public class PageDataResponse <T> extends Response implements Serializable {
    protected Integer pageSize;
    protected Integer pageNumber;
    protected Integer totalRecord = 0;
    protected List<T> pageData;

    public PageDataResponse() {
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public Integer getTotalRecord() {
        return this.totalRecord;
    }

    public List<T> getPageData() {
        return this.pageData;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    public String toString() {
        return "PageDataResponse(super=" + super.toString() + ", pageSize=" + this.getPageSize() + ", pageNumber=" + this.getPageNumber() + ", totalRecord=" + this.getTotalRecord() + ", pageData=" + this.getPageData() + ")";
    }
}

