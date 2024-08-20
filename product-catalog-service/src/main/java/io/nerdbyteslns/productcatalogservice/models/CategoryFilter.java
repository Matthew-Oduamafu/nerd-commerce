package io.nerdbyteslns.productcatalogservice.models;

import io.nerdbyteslns.nerdcommercecore.models.BaseFilter;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CategoryFilter extends BaseFilter {
    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;
    private final String sortDir;

    public CategoryFilter(int page, int pageSize, LocalDateTime fromDate, LocalDateTime toDate, String sortDir) {
        super(page, pageSize);
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.sortDir = sortDir;
    }
}
