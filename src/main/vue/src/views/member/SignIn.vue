
<template>
    <div id="app">
        <form @submit.prevent="submitSignIn">
            <div>
                <label for="email">email</label>
                <input type="email" id="email" v-model="email" required>
            </div>
            <div>
                <label for="password">비밀번호</label>
                <input type="password" id="password" v-model="password" required>
            </div>
            <div>
                <button type="submit">로그인</button>
            </div>
        </form>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "SignIn",
    data() {
        return {
            email: '',
            password: '',
        };
    },
    methods:{
        async submitSignIn() {
            const signInRequest = {
                email : this.email,
                password : this.password
            }

            try {
                const response = await axios.post("http://localhost:8080/todo/member/signIn", signInRequest);

                if(response.status === 200) {
                    console.log(response);

                    alert("로그인 성공");

                    localStorage.setItem("user", JSON.stringify(response.data.data));
                    
                    this.$emit('loginSuccess'); // 부모 컴포넌트에 로그인 성공 이벤트 전파
                    this.$router.push('/');
                }
                
            } catch (error) {
                alert("로그인 실패");
                console.log("error : ", error);
            }
        },//submitSignIn
    }
}
</script>