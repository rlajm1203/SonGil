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
        memberType: "helper", // 고정값
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