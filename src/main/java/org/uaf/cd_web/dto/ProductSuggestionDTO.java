package org.uaf.cd_web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSuggestionDTO {
    private String idPr;
    private String name;
    private String priceNow;
    private String imageUrl;
}
