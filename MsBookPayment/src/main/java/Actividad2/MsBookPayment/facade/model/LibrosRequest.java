package Actividad2.MsBookPayment.facade.model;

import java.util.List;

public class LibrosRequest {

    private List<Long> bookIds;

    public LibrosRequest(List<Long> ids) {
        this.bookIds = ids;
    }

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }
}
