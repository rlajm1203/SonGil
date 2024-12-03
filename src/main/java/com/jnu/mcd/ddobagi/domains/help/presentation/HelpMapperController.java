package com.jnu.mcd.ddobagi.domains.help.presentation;

import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponse;
import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponseBody;
import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponseGenerator;
import com.jnu.mcd.ddobagi.common.presentation.response.MessageCode;
import com.jnu.mcd.ddobagi.domains.help.application.dto.HelpResponse;
import com.jnu.mcd.ddobagi.domains.help.application.dto.HelpResponses;
import com.jnu.mcd.ddobagi.domains.help.application.service.HelpService;
import com.jnu.mcd.ddobagi.domains.help.persistence.Help;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/helps")
    public ApiResponse<ApiResponseBody.SuccessBody<HelpResponses>> getHelps(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){

        List<Help> helps = helpService.getMyHelp(page, size);

        List<HelpResponse> responses = helps.stream().map(HelpResponse::from).toList();

        return ApiResponseGenerator.success(new HelpResponses(responses), HttpStatus.OK, MessageCode.GET);

    }

}
