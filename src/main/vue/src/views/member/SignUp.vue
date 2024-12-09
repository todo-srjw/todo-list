<template>
    <div id="app">
        <form @submit.prevent="submitSignUp">
            <div>
                <label for="email">email</label>
                <input type="email" id="email" v-model="email" required>
                <button type="button" @click.prevent="checkEmailDuplicate">중복확인</button>
            </div>
            <div>
                <label for="password">비밀번호</label>
                <input type="password" id="password" v-model="password" required>
            </div>
            <div>
                <button type="submit">회원가입</button>
            </div>
        </form>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name : "SignUp",
    data() {
        return {
            email: '',
            password: '',
            emailCheckCode: null,
        };
    },
    watch: {
    email() {
            this.emailCheckCode = null;
        }
    },
    methods: {
        async checkEmailDuplicate() {
            const email = this.email;

            if (!this.isValidEmail(email)) {
                return;
            }

            try {
                const response = await axios.get(`http://localhost:8081/todo/member/check-email?email=${email}`);
                
                this.emailCheckCode = response.status;

                alert("사용 가능한 이메일 입니다.");
                console.log(response);
            } catch (error) {
                alert("이미 사용 중인 이메일입니다.");
                console.log("error : ", error);
            }
        },//checkEmailDuplicate
        isValidEmail(email){
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if(!email){
                alert("이메일을 입력하세요.");
                return;
            }

            if(!emailRegex.test(email)){
                alert("이메일 형식이 올바르지 않습니다.");
                return;
            }

            return true;
        },//isValidEmail
        async submitSignUp() {
            if(this.emailCheckCode != 200){
                alert("중복확인 버튼을 눌러주세요");
                return;
            }

            const signUpRequest = {
                email : this.email,
                password : this.password
            };

            try {
                const response = await axios.post("http://localhost:8081/todo/member/signUp", signUpRequest);
                console.log(response);

                alert("회원가입 성공");
                
                this.$router.push('/');
            } catch (error) {
                console.log(error);
            }
        },//submitSignUp
    },
};
</script>