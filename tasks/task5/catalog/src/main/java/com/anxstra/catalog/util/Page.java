package com.anxstra.catalog.util;

public record Page(int pageNumber, int pageSize) {

    public long getOffset() {
        return (long) this.pageNumber * (long) this.pageSize;
    }
}
