package common.cdi;



import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kacper on 2014-10-19.
 */
public abstract class PaginationBean implements Serializable {

    protected int pageSize;

    protected int currentPageIndex;

    private Map<String, String> paramMap;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }

    protected Map<String, String> getRequestParamMap() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    }

    public Map<String, String> getParamMap() {
        if (paramMap == null) {
            paramMap = new HashMap<>();
        }
        return paramMap;
    }

    public void change() {
        if (getParamMap().containsKey("ps")) {
            getParamMap().remove("ps");
        }
        getParamMap().put("ps", String.valueOf(pageSize));
    }
}
