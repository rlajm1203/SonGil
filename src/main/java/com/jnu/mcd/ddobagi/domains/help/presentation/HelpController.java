package com.jnu.mcd.ddobagi.domains.help.presentation;


import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponse;
import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponseBody;
import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponseGenerator;
import com.jnu.mcd.ddobagi.common.presentation.response.MessageCode;
import com.jnu.mcd.ddobagi.domains.help.application.dto.HelpRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/helps")
@RequiredArgsConstructor
public class HelpController {

    private final HelpService helpService;

    @PostMapping
    public ApiResponse<ApiResponseBody.SuccessBody<Map<String,Long>>> crateOnceHelp(
            @RequestBody HelpRequest helpRequest
            ){

        Long helpId = helpService.createHelp(helpRequest);

        return ApiResponseGenerator.success(Map.of("id", helpId), HttpStatus.OK, MessageCode.CREATE);

    }

    @GetMapping("/{helpId}")
    public ApiResponse<ApiResponseBody.SuccessBody<HelpResponse>> getHelp(
            @PathVariable("helpId") Long helpId
    ){
        HelpResponse response = helpService.getHelp(helpId);

        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.GET);
    }

    @GetMapping
    public ApiResponse<ApiResponseBody.SuccessBody<HelpResponses>> getHelps(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size
    ){

        List<Help> helps = helpService.getHelpPage(page, size, null);
        List<HelpResponse> helpToResponse = helps.stream().map(HelpResponse::from).toList();

        //TODO: 조회 작성
        return ApiResponseGenerator.success(new HelpResponses(helpToResponse), HttpStatus.OK, MessageCode.GET);
    }

}
