package org.rusty;

import lombok.RequiredArgsConstructor;
import org.rusty.rest.StatsTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommonController {

    private final CommonService commonService;

    @GetMapping("/mInfo")
    public List<StatsTable> mInfo() {
        return commonService.getMTable();
    }

    @GetMapping("/wInfo")
    public List<StatsTable> wInfo() {
        return commonService.getWTable();
    }
}
