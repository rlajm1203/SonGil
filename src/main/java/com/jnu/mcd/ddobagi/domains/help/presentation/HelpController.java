package com.jnu.mcd.ddobagi.domains.help.presentation;


import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponse;
import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponseBody;
import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponseGenerator;
import com.jnu.mcd.ddobagi.common.presentation.response.MessageCode;
import com.jnu.mcd.ddobagi.domains.help.application.dto.HelpRequest;
import com.jnu.mcd.ddobagi.domains.help.application.dto.OnceHelpResponse;
import com.jnu.mcd.ddobagi.domains.help.application.service.HelpService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ApiResponse<ApiResponseBody.SuccessBody<OnceHelpResponse>> getHelp(
            @PathVariable("helpId") Long helpId
    ){
        OnceHelpResponse response = helpService.getHelp(helpId);

        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.GET);
    }

}
