package org.rusty.rest;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StatsTable {

    List<TeamRow> rows = new ArrayList<>();
}
