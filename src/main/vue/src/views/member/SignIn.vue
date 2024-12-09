
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
                
                console.log(response.data);
            } catch (error) {
                console.log("error : ", error);
            }
        },//submitSignIn
    }
}
</script>