package io.nerdbyteslns.nerdcommercecore.models;


import lombok.Data;

@Data
public abstract class BaseFilter {
    private final int page;
    private final int pageSize;

    public BaseFilter(int page, int pageSize) {
        this.page = Math.max(page, 1);
        this.pageSize = pageSize > 0 ? pageSize : 10;
    }

    public BaseFilter() {
        this(1, 10);
    }

    public int getOffset() {
        return (page - 1) * pageSize;
    }

    public int getLimit() {
        return pageSize;
    }
}
