<template>
  <div class="post-display">
    <h2>todo-list</h2>

    <table>
      <thead>
      <tr>
        <th>순번</th>
        <th>글쓴이</th>
        <th>제목</th>
        <th>날짜</th>
      </tr>
      </thead>
      <tbody>
      <!-- 각 계획 데이터를 테이블 행으로 출력 -->
      <tr v-for="(post, index) in data" :key="post.id">
        <td>{{ index + 1 }}</td>
        <td>{{ post.author }}</td>
        <td>{{ post.title }}</td>
        <td>{{ post.date }}</td>
      </tr>
      </tbody>
    </table>

  </div>

</template>

<script>
import axios from 'axios';

export default {
  name: "PostPage",
  data(){
    return{
      data:[]
    };
  },
  created(){
    axios.get('/post')
        .then(response => {
          this.data = response.data;
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        })
  }
}
</script>


<style scoped>

</style>