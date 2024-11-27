package com.jnu.mcd.ddobagi.domains.help.presentation;

import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponse;
import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponseBody;
import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponseGenerator;
import com.jnu.mcd.ddobagi.common.presentation.response.MessageCode;
import com.jnu.mcd.ddobagi.domains.help.application.service.HelpService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mapper")
public class HelpMapperController {

    private final HelpService helpService;

    @PostMapping("/helps/{helpId}")
    public ApiResponse<ApiResponseBody.SuccessBody<Map<String, Long>>> matchHelp(
            @PathVariable("helpId") Long helpId
    ){
        Long response = helpService.match(helpId);

        return ApiResponseGenerator.success(Map.of("id", response), HttpStatus.OK, MessageCode.CREATE);
    }

}
