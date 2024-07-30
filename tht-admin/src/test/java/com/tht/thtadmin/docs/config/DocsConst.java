package com.tht.thtadmin.docs.config;

import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public abstract class DocsConst {

    public static FieldDescriptor[] getPagingFieldDescriptors(List<FieldDescriptor> apiFieldDescriptors) {

        List<FieldDescriptor> fieldDescriptors = new ArrayList<>(List.of(
            fieldWithPath("pageable.sort.empty").description("정렬이 비어있는지 여부"),
            fieldWithPath("pageable.sort.sorted").description("내용이 정렬되었는지 여부"),
            fieldWithPath("pageable.sort.unsorted").description("내용이 정렬되지 않았는지 여부"),
            fieldWithPath("pageable.offset").description("현재 페이지의 오프셋"),
            fieldWithPath("pageable.pageNumber").description("현재 페이지 번호"),
            fieldWithPath("pageable.pageSize").description("페이지당 항목 수"),
            fieldWithPath("pageable.paged").description("페이지 매김이 활성화되었는지 여부"),
            fieldWithPath("pageable.unpaged").description("페이지 매김이 비활성화되었는지 여부"),
            fieldWithPath("last").description("마지막 페이지인지 여부"),
            fieldWithPath("totalElements").description("전체 요소 수"),
            fieldWithPath("totalPages").description("전체 페이지 수"),
            fieldWithPath("first").description("첫 번째 페이지인지 여부"),
            fieldWithPath("size").description("페이지 크기"),
            fieldWithPath("number").description("현재 페이지 번호"),
            fieldWithPath("sort.empty").description("정렬이 비어있는지 여부"),
            fieldWithPath("sort.sorted").description("내용이 정렬되었는지 여부"),
            fieldWithPath("sort.unsorted").description("내용이 정렬되지 않았는지 여부"),
            fieldWithPath("numberOfElements").description("현재 페이지의 요소 수"),
            fieldWithPath("empty").description("현재 페이지가 비어 있는지 여부")
        ));

        fieldDescriptors.addAll(apiFieldDescriptors);
        return fieldDescriptors.toArray(FieldDescriptor[]::new);
    }
}
