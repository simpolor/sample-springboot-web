package io.simpolor.pageable.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class PageDto<T> {

    private Page page;

    private List<T> content;

    public static <T> PageDto<T> of(List<T> content, Long totalCount){

        PageDto<T> response = new PageDto();
        response.page = Page.of(totalCount);
        response.content = content;

        return response;
    }

    public static <T> PageDto<T> ofEmpty(){

        PageDto<T> response = new PageDto();
        response.page = Page.of(0L);
        response.content = Collections.EMPTY_LIST;

        return response;
    }

    @Getter
    @Setter
    public static class Page {

        private Long totalCount;

        public static Page of(Long totalCount){

            Page page = new Page();
            page.setTotalCount(totalCount);

            return page;
        }
    }
}
