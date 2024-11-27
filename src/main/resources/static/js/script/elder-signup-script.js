document.getElementById("signup-form").addEventListener("submit", (event) => {
    event.preventDefault(); // 기본 폼 제출 방지

    // 폼 데이터 가져오기
    const id = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;
    const email = document.getElementById("email").value;
    const name = document.getElementById("name").value;
    const protectorPhoneNumber = document.getElementById("protector-phone").value;

    // 비밀번호 확인 검증
    if (password !== confirmPassword) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
    }

    // JSON 데이터 생성
    const requestBody = {
        id: id,
        password: password,
        email: email,
        name: name,
        memberType: "elderly", // 고정값
        protectorPhoneNumber: protectorPhoneNumber
    };

    // HTTP POST 요청
    fetch("http://localhost:8080/api/v1/auth/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(requestBody)
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                return response.json().then(err => { throw new Error(err.message); });
            }
        })
        .then(data => {
            alert("회원가입 성공: " + data.message);
            // 메인 페이지로 리다이렉트
            window.location.href = "/";
        })
        .catch(error => {
            console.error(error);
            alert("회원가입 중 오류가 발생했습니다: " + error.message);
        });
});

document.addEventListener("DOMContentLoaded", function () {
    const usernameInput = document.getElementById("username");
    const checkButton = document.getElementById("check-username");
    const messageDiv = document.getElementById("username-message");

    // 중복체크 버튼 클릭 이벤트
    checkButton.addEventListener("click", function () {
        const username = usernameInput.value.trim(); // 입력값 가져오기

        // 입력값이 비어있는 경우
        if (!username) {
            showMessage("아이디를 입력해주세요.", "error");
            return;
        }

        // 버튼 비활성화 (중복체크 요청 중)
        checkButton.disabled = true;

        // HTTP POST 요청
        fetch("http://localhost:8080/api/v1/auth/dupl-check", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: username, // JSON 형식으로 전달
        })
            .then((response) => response.json())
            .then((data) => {
                if (data.status === "500" || data.code === "500") {
                    // 사용할 수 없는 아이디인 경우
                    showMessage(data.message || "사용할 수 없는 아이디입니다.", "error");
                } else {
                    // 아이디 사용 가능
                    showMessage("사용 가능한 아이디입니다.", "success");
                }
            })
            .catch((error) => {
                console.error("중복체크 요청 중 오류 발생:", error);
                showMessage("서버와의 통신 중 오류가 발생했습니다.", "error");
            })
            .finally(() => {
                // 버튼 다시 활성화
                checkButton.disabled = false;
            });
    });

    // 메시지 표시 함수
    function showMessage(message, type) {
        messageDiv.textContent = message;
        messageDiv.className = `message ${type}`;
    }
});