package com.neu.admin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BoardDataDTO {

    private long waitForGiveInsNum;

    private long waitInsToTestNum;

    private long dealIdeaNum;

    private long ideaNumber;

}
