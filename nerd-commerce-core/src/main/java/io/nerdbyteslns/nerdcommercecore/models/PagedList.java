package io.nerdbyteslns.nerdcommercecore.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PagedList<T> extends BaseFilter {
    private final long total;
    private final List<T> data;
    private final int lowerBound;
    private final int upperBound;
    private final int totalPages;
    private final boolean hasPrevious;
    private final boolean hasNext;

    public PagedList(int page, int pageSize, long total, List<T> data) {
        super(page, pageSize);
        this.total = total;
        this.data = data;

        lowerBound = (page - 1) * pageSize + 1;
        upperBound = Math.min(page * pageSize, (int) total);
        totalPages = (int) Math.ceil((double) total / pageSize);
        hasPrevious = page > 1;
        hasNext = page < totalPages;
    }

    public boolean hasPrevious() {
        return hasPrevious;
    }
    public boolean hasNext() {
        return hasNext;
    }
}
