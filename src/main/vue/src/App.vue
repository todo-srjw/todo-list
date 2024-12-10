<template>
  <div id="app">
    <header v-if="$route.path == '/'">
      <h1>My Vue App</h1>
      <!-- 페이지 이동 버튼 -->
      <div><router-link to="/">Go to Home</router-link><br/></div>  <!-- 홈으로 이동 -->
      <!-- 로그인 안된 상태에서만 보여주는 버튼 -->
      <div v-if="!isLoggedIn">
        <router-link to="/sign-up">Go to SignUp</router-link>
      </div>
      <div v-if="!isLoggedIn">
        <router-link to="/sign-in">Go to SignIn</router-link>
      </div>
      <!-- 로그인한 경우에만 보여주는 로그아웃 버튼 -->
      <div v-if="isLoggedIn">
        <a href="#" @click="logout">{{ storedUser }} Log Out</a>
      </div>
<!--      <router-link to="/">Go to Home</router-link><br/>  &lt;!&ndash; 홈으로 이동 &ndash;&gt;-->
      <router-link to="/board-list">Go to BoardList</router-link><br/> <!-- 게시판 리스트로 이동 -->
      <router-link to="/todo-list">Go to TodoList</router-link> <br/><!-- to do 리스트로 이동 -->
      <router-link to="/notification-list">Go to NotificationList</router-link> <br/><!-- 알림 리스트로 이동 -->
    </header>
    <!-- URL에 따라 해당 컴포넌트를 렌더링 -->
    <router-view @loginSuccess="onLoginSuccess"/>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'App',
  data() {
    return {
      storedUser : '',
      isLoggedIn: false, // 로그인 상태
    };
  },
  created() {
    this.updateLoginStatus(); // 초기 로그인 상태 체크
  },
  methods: {
    // 로그인 상태 체크
    updateLoginStatus() {
      const user = JSON.parse(localStorage.getItem("user"));

      if (user) {
        this.storedUser = user;
        this.isLoggedIn = true;

      } else {
        this.storedUser = '';
        this.isLoggedIn = false;
      }
    },
    // 로그인 성공 이벤트 처리
    onLoginSuccess() {
      this.updateLoginStatus(); // 로그인 상태 갱신
    },
    logout() {
      axios.post("http://localhost:8080/todo/member/signOut")
        .then(() => {
            this.isLoggedIn = false; // 로그인 상태 초기화
            this.storedUser = ''; // 사용자 정보 초기화
            localStorage.removeItem("user"); // 로컬 스토리지 초기화
            //this.$router.push("/"); // 홈으로 리다이렉트
            alert("로그아웃 성공");
        })
        .catch(error => {
            console.error("로그아웃 실패: ", error);
        });
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
