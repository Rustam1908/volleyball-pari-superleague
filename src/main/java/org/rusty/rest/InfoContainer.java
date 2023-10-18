package org.rusty.rest;

import lombok.Data;

import java.util.List;

@Data
public class InfoContainer {

    private List<StatsTable> tables;
}
