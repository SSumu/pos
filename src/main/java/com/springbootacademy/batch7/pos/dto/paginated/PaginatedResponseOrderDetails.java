package com.springbootacademy.batch7.pos.dto.paginated;

import com.springbootacademy.batch7.pos.dto.response.ResponseOrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseOrderDetails {
    // We give the incoming data in here.
    private List<ResponseOrderDetailsDTO> list; // Thing inside the <> is decided as per our requirements. So here the Generic type of the List is the ResponseOrderDetailsDTO.
    private long dataCount;
}
