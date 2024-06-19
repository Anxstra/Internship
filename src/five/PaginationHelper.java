package five;

import java.util.List;

public class PaginationHelper<I> {

    private final List<I> collection;

    private final int itemsPerPage;

    /**
     * The constructor takes in an array of items and an integer indicating how many
     * items fit within a single page
     */
    public PaginationHelper(List<I> collection, int itemsPerPage) {
        this.collection = collection;
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return collection.size();
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        return collection.size() % itemsPerPage == 0 ? collection.size() / itemsPerPage
                : collection.size() / itemsPerPage + 1;
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        if (pageIndex < 0 || pageIndex > pageCount() - 1) {
            return -1;
        }
        return pageIndex < pageCount() - 1 ? itemsPerPage : collection.size() - (pageCount() - 1) * itemsPerPage;
    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        if (itemIndex < 0 || itemIndex >= collection.size()) {
            return -1;
        }
        return itemIndex / itemsPerPage;
    }

    public static void main(String[] args) {
        List<Character> collection = List.of('a', 'b', 'c', 'd', 'e', 'f');
        PaginationHelper<Character> helper = new PaginationHelper<>(collection, 4);
        System.out.println(2 == helper.pageCount());
        System.out.println(6 == helper.itemCount());
        System.out.println(4 == helper.pageItemCount(0));
        System.out.println(2 == helper.pageItemCount(1));
        System.out.println(-1 == helper.pageItemCount(2));
        System.out.println( 1 == helper.pageIndex(5));
        System.out.println( 0 == helper.pageIndex(2));
        System.out.println(-1 == helper.pageIndex(20));
        System.out.println(-1 == helper.pageIndex(-10));
    }
}
