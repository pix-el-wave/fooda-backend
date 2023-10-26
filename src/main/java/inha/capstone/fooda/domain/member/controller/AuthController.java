package inha.capstone.fooda.domain.member.controller;

import inha.capstone.fooda.domain.common.response.DataResponse;
import inha.capstone.fooda.domain.member.dto.PostSigninMemberReqDto;
import inha.capstone.fooda.domain.member.dto.PostSigninMemberResDto;
import inha.capstone.fooda.domain.member.dto.PostSignupMemberReqDto;
import inha.capstone.fooda.domain.member.dto.PostSignupMemberResDto;
import inha.capstone.fooda.domain.member.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Login & SignUp")
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "회원가입 API",
            description = "<p>회원 정보를 바탕으로 회원 가입을 진행합니다.</p>" +
                    "<p>회원가입에 성공하면 회원가입된 사용자의 pk 를 응답합니다.</p>"
    )
    @PostMapping(value = "/local/new")
    public ResponseEntity<DataResponse<PostSignupMemberResDto>> signUp(
            @Validated @RequestBody PostSignupMemberReqDto postSignupMemberReqDto) {
        Long memberId = authService.signUp(postSignupMemberReqDto.toDto());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DataResponse<>(
                        PostSignupMemberResDto.of(memberId)
                ));
    }

    @Operation(
            summary = "JWT 로그인 요청 API",
            description = "<p>로그인하려는 아이디와 패스워드로 서버에 로그인합니다.</p>" +
                    "<p>로그인에 성공하면 jwt access token 을 응답합니다.</p>" +
                    "<p><strong>이후 로그인 권한이 필요한 API를 호출할 때는 HTTP header에 다음과 같이 access token을 담아서 요청해야 합니다.</strong></p>" +
                    "<ul><li><code>Authorization: Bearer 서버로부터_받은_액세스_토큰</code></li></ul>"
    )
    @PostMapping("/local")
    public ResponseEntity<DataResponse<PostSigninMemberResDto>> signIn(
            @Valid @RequestBody PostSigninMemberReqDto postSigninMemberReqDto
    ) {
        String jwtToken = authService.signIn(postSigninMemberReqDto.getUsername(), postSigninMemberReqDto.getPassword());

        PostSigninMemberResDto response = PostSigninMemberResDto.of(jwtToken);

        return new ResponseEntity<>(
                new DataResponse<>(response),
                HttpStatus.OK
        );
    }
}