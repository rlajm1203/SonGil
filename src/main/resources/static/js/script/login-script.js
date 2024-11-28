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
            if (!response.ok) throw new Error('로그인 실패');
            return response.json();
        })
        .then(data => {
            if (data.accessToken) {
                localStorage.setItem('accessToken', data.accessToken);
                alert('로그인 성공!');
                window.location.href = '/view/index';
            } else {
                throw new Error('Access Token이 없습니다.');
            }
        })
        .catch(error => {
            alert('로그인에 실패했습니다. 다시 시도해주세요.');
            console.error('오류:', error);
        });
});