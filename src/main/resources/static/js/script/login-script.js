document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("login-form");

    form.addEventListener("submit", function (event) {
        event.preventDefault(); // 기본 폼 제출 방지

        const username = document.getElementById("username").value.trim();
        const password = document.getElementById("password").value.trim();

        // 요청 데이터 준비
        const requestData = {
            id: username,
            password: password,
        };

        // 서버로 로그인 요청
        fetch("http://localhost:8080/api/v1/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(requestData),
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("로그인 실패");
                }
                return response.json();
            })
            .then((data) => {
                alert("로그인 성공");
                // Access Token 저장 (옵션)
                localStorage.setItem("accessToken", data.accessToken);
                // 메인 페이지로 리디렉션
                window.location.href = "/";
            })
            .catch((error) => {
                console.error("로그인 요청 중 오류 발생:", error);
                alert("아이디 또는 비밀번호가 올바르지 않습니다.");
            });
    });
});