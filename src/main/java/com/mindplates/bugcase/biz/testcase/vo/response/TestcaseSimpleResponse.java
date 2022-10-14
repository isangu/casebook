package com.mindplates.bugcase.biz.testcase.vo.response;

import com.mindplates.bugcase.biz.testcase.entity.Testcase;
import lombok.Data;

@Data
public class TestcaseSimpleResponse {

    private Long id;

    private String seqId;
    private String name;
    private Integer itemOrder;
    private Boolean closed;

    public TestcaseSimpleResponse(Testcase testcase) {
        this.id = testcase.getId();
        this.seqId = testcase.getSeqId();
        this.name = testcase.getName();
        this.itemOrder = testcase.getItemOrder();
        this.closed = testcase.getClosed();
    }

}
