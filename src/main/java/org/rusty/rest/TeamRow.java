package org.rusty.rest;

import lombok.Data;

import java.util.List;

@Data
public class TeamRow {

    private String imageURI;
    private String name;
    private int games;
    private int win3;
    private int win2;
    private int loss1;
    private int loss;
    private int sets;
    private int points;
    private int pointsPercent;
    private List<Integer> performance;
}
