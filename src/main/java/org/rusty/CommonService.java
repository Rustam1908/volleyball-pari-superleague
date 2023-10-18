package org.rusty;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.rusty.rest.Performance;
import org.rusty.rest.StatsTable;
import org.rusty.rest.TeamRow;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CommonService {

    String mTableURL = "https://www.championat.com/volleyball/_msuperliga/tournament/5700/table/";
    String wTableURL = "https://www.championat.com/volleyball/_wsuperliga/tournament/5688/table/";

    public List<StatsTable> getMTable() {
        Document mDocument = getDocument(mTableURL);
        return parseTables(mDocument.select("tbody"));
    }

    public List<StatsTable> getWTable() {
        Document mDocument = getDocument(wTableURL);
        return parseTables(mDocument.select("tbody"));
    }

    private Document getDocument(String URL) {
        Document doc;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return doc;
    }

    private List<StatsTable> parseTables(Elements tables) {
        List<StatsTable> statsTables = new ArrayList<>();
        for (Element table : tables) {
            StatsTable statsTable = new StatsTable();
            statsTable.setRows(parseTeams(table.select("tr")));
            statsTables.add(statsTable);
        }
        return statsTables;
    }

    private List<TeamRow> parseTeams(Elements teams) {
        List<TeamRow> teamRows = new ArrayList<>();
        for (Element team : teams) {
            teamRows.add(parseTeam(team));
        }
        return teamRows;
    }

    private TeamRow parseTeam(Element team) {
        TeamRow.TeamRowBuilder trBuilder = TeamRow.builder();
        Elements teamInfo = team.select("td");
        trBuilder.logoURI(teamInfo.get(1).getElementsByClass("table-item__logo").select("img").attr("src"))
                .name(teamInfo.get(1).getElementsByClass("table-item__name").text())
                .games(teamInfo.get(2).text())
                .win3(teamInfo.get(3).text())
                .win2(teamInfo.get(4).text())
                .loss1(teamInfo.get(5).text())
                .loss(teamInfo.get(6).text())
                .sets(teamInfo.get(7).text())
                .points(teamInfo.get(8).text())
                .pointsPercent(teamInfo.get(9).text())
                .performances(parseTeamPerformance(teamInfo.get(10)));
        return trBuilder.build();
    }

    private List<Performance> parseTeamPerformance(Element performanceInfo) {
        List<Performance> performances = new ArrayList<>();
        Elements matchBlock = performanceInfo.select("a");
        for (Element match : matchBlock) {
            Performance performance = new Performance();
            Element performanceBlock = match.select("span").get(0);
            performance.setResult(performanceBlock.className());
            performance.setInfo(performanceBlock.attr("title"));
            performances.add(performance);
        }
        return performances;
    }
}