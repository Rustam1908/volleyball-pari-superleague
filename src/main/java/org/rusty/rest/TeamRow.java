package org.rusty.rest;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TeamRow {

    private String logoURI;
    private String name;
    private String games;
    private String win3;
    private String win2;
    private String loss1;
    private String loss;
    private String sets;
    private String points;
    private String pointsPercent;
    private List<String> performance;
}
