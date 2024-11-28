document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("signup-form");
    const submitButton = document.getElementById("submit-button");
    const usernameInput = document.getElementById("username");
    const passwordInput = document.getElementById("password");
    const confirmPasswordInput = document.getElementById("confirm-password");
    const emailInput = document.getElementById("email");
    const nameInput = document.getElementById("name");
    const protectorPhoneInput = document.getElementById("protector-phone");
    const usernameMessage = document.getElementById("username-message");

    // 아이디 중복 확인 함수
    window.checkUsername = function () {
        const username = usernameInput.value.trim();
        if (!username) {
            usernameMessage.textContent = "아이디를 입력해주세요.";
            usernameMessage.style.color = "red";
            return;
        }

        fetch("http://localhost:8080/api/v1/auth/dupl-check", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(username),
        })
            .then((response) => response.json())
            .then((data) => {
                if (data.status === "500") {
                    usernameMessage.textContent = data.message; // 사용 불가능한 아이디 메시지
                    usernameMessage.style.color = "red";
                    submitButton.disabled = true;
                } else {
                    usernameMessage.textContent = "사용 가능한 아이디입니다.";
                    usernameMessage.style.color = "green";
                    submitButton.disabled = false;
                }
            })
            .catch((error) => {
                console.error("아이디 중복 확인 중 오류 발생:", error);
                usernameMessage.textContent = "오류가 발생했습니다. 다시 시도해주세요.";
                usernameMessage.style.color = "red";
                submitButton.disabled = true;
            });
    };

    // 폼 제출 핸들러
    form.addEventListener("submit", function (event) {
        event.preventDefault();

        // 비밀번호 확인
        if (passwordInput.value !== confirmPasswordInput.value) {
            alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return;
        }

        // 회원가입 요청 데이터 생성
        const requestData = {
            id: usernameInput.value.trim(),
            password: passwordInput.value.trim(),
            email: emailInput.value.trim(),
            name: nameInput.value.trim(),
            memberType: "helper", // 고정 값
            protectorPhoneNumber: protectorPhoneInput.value.trim(),
        };

        // 회원가입 요청 전송
        fetch("http://localhost:8080/api/v1/auth/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(requestData),
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("회원가입 요청에 실패했습니다.");
                }
                return response.json();
            })
            .then((data) => {
                alert("회원가입이 완료되었습니다.");
                window.location.href = "/"; // 메인 페이지로 리디렉션
            })
            .catch((error) => {
                console.error("회원가입 실패:", error);
                alert("회원가입 중 문제가 발생했습니다. 다시 시도해주세요.");
            });
    });
});