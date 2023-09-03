package org.starkov.tests.api.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class GetPublicRespModel {
    private String publicInvestorName;
    private List<PublicInvestorLinks> publicInvestorLinks;
    private String publicInvestorLogo;
    private String file;
}



