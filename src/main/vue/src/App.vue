<template>
  <div id="app">
    <header v-if="$route.path !== '/board-list'">
      <h1>My Vue App</h1>
      <!-- 페이지 이동 버튼 -->
      <router-link to="/">Go to Home</router-link><br/>  <!-- 홈으로 이동 -->
      <router-link to="/board-list">Go to BoardList</router-link> <!-- 게시판 리스트로 이동 -->
    </header>
    <!-- URL에 따라 해당 컴포넌트를 렌더링 -->
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
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
