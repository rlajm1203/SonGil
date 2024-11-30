document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const id = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const loginData = { id, password };

    fetch('/api/v1/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
        .then(response => {
            const response_data = response.json();

            if (!response.ok) {
                message = response_data.message;
                console.log(message)
                throw new Error(message.toString());
            }
            return response_data;
        })
        .then(data => {
            // 'data' 객체 내부에 접근
            const accessToken = data.data.accessToken; // accessToken 추출
            const accessExpiredTime = data.data.accessExpiredTime; // 만료 시간 추출
            const message = data.message; // 성공 메시지 추출

            // Access Token 저장
            if (accessToken) {
                localStorage.setItem('accessToken', accessToken);
                localStorage.setItem('accessExpiredTime', accessExpiredTime);
                alert('로그인 성공: ' + message);
                window.location.href = '/view/index'; // 성공 후 리다이렉션
            } else {
                throw new Error('Access Token이 없습니다.');
            }
        })
        .catch(error => {
            alert(error.message);
            console.error('오류:', error);
        });
});