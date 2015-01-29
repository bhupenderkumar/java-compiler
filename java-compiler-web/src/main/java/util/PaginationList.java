package util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Kacper on 2014-10-19.
 */
public class PaginationList<T> extends ArrayList<T> {

    public PaginationList(Collection<? extends T> c, int currentPage) {
        super(c);
        this.currentPage = currentPage;
    }

    private static final long serialVersionUID = 1063243001257700036L;

    private int totalPages;

    private int currentPage;

    public void setTotalPages(int totalEntries, int pageSize) {
        totalPages = (int)Math.ceil(totalEntries / pageSize);
    }

    public boolean hasNext() {
        return currentPage + 1 < totalPages;
    }

    public boolean hasPrevious() {
        return currentPage > 0;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int retTotalPages() {
        return totalPages;
    }
}