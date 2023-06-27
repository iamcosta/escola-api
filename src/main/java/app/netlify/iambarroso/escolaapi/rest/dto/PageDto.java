package app.netlify.iambarroso.escolaapi.rest.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageDto<T> {

    private List<T> data;
    private Integer totalPages;
    private Long totalElements;
    private Integer pageSize;
    private Integer pageNumber;

    public static <T> PageDto<T> fromPage(Page<T> page) {
        PageDto<T> dto = new PageDto<>();
        dto.setData(page.getContent());
        dto.setTotalPages(page.getTotalPages());
        dto.setTotalElements(page.getTotalElements());
        dto.setPageSize(page.getSize());
        dto.setPageNumber(page.getNumber());

        return dto;
    }
}
