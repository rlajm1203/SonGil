function signup(role) {
    if (role === 'elderly') {
        // alert("노인으로 가입하는 페이지로 이동합니다.");
        window.location.href="/view/elderly-signup"
    } else if (role === 'helper') {
        // alert("도우미로 가입하는 페이지로 이동합니다.");
        window.location.href="/view/helper-signup"
    }
}