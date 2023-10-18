package org.rusty;

import lombok.RequiredArgsConstructor;
import org.rusty.rest.InfoContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommonController {

    private final CommonService commonService;

    @GetMapping("/mInfo")
    public InfoContainer mInfo() {
        return commonService.getMTable();
    }

    @GetMapping("/wInfo")
    public InfoContainer wInfo() {
        return commonService.getWTable();
    }
}
