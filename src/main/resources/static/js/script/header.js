// 모달 열기
function openModal() {
    document.getElementById('logout-modal').style.display = 'block';
}

// 모달 닫기
function closeModal() {
    document.getElementById('logout-modal').style.display = 'none';
}

// 로그아웃
function logout() {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('accessTokenExpiration');
    alert('로그아웃 되었습니다.');
    location.reload();
}