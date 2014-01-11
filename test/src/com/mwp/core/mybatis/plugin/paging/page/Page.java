
package com.mwp.core.mybatis.plugin.paging.page;

import org.apache.ibatis.session.RowBounds;

public class Page extends RowBounds{
    
    public Page(int pageNo, int pageSize) {
        super((pageNo - 1) * pageSize, pageSize);
    }
    
    private Integer count;
    
    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }
    
    /**
     * @param count the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }
    
}
