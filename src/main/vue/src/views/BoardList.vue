<template>
  <div id="app">
    <!-- 게시판 제목 -->
    <div class="board-title">
      <h2>게시판</h2>
    </div>

    <!-- 게시글 목록을 보여주는 테이블 -->
    <table class="table">
      <tr class="header">
        <td class="num">번호</td>
        <td class="title">제목</td>
        <td class="author">작성자</td>
        <td class="createdDate">작성날짜</td>
      </tr>
      <tr v-for="board in boards" :key="board.id">
        <td>{{ board.id }}</td>
        <td>{{ board.title }}</td>
        <td>{{ board.author }}</td>
        <!-- 변환된 날짜 출력 -->
        <td>{{ formatDate(board.createdDate) }}</td>
      </tr>
    </table><br/>
    <table>
      <tr>
        <td><button onclick="location.href='write.html'">글쓰기</button></td>
      </tr>
    </table>
  </div>
</template>
<script>
import axios from 'axios'

export default {
  name: 'BoardList',
  data() {
    return {
      boards: []  // 게시글 데이터를 저장할 변수
    }
  },
  mounted() {
    // 컴포넌트가 마운트되면 데이터 자동으로 가져오기
    this.getData();
  },
  methods: {
    // API 호출하여 게시글 데이터를 가져오는 함수
    getData() {
      axios.get('http://localhost:8080/todo/board')  // Spring Boot API 경로
          .then(response => {
            this.boards = response.data  // API로부터 받은 데이터를 boards 배열에 저장
            console.log("board controller : "+response.data);
          })
          .catch(error => {
            console.error('데이터를 가져오는 데 오류가 발생했습니다:', error)
          })
    },
    // 날짜를 YYYY-MM-DD 형식으로 변환하는 함수
    formatDate(dateString) {
      const date = new Date(dateString);  // 문자열을 Date 객체로 변환
      const year = date.getFullYear();  // 연도
      const month = String(date.getMonth() + 1).padStart(2, '0');  // 월 (01, 02, ...)
      const day = String(date.getDate()).padStart(2, '0');  // 일 (01, 02, ...)
      return `${year}-${month}-${day}`;  // YYYY-MM-DD 형식으로 반환
    }
  }
}
</script>

<style>
table {
  margin: auto;
  width: 700px;
  border-radius: 5px;
  border-collapse: collapse;
  border-top: none;
}
.header {
  background-color: rgb(218, 231, 255);
  text-align: center;
}
.table td, .table th {
  border-bottom: 1px lightgray solid;
  height: 30px;
  font-size: 14px;
}
.num {
  width: 50px;
}
.title {
  width: 500px;
}
.body {
  text-align: center;
}
.body .title {
  text-align: left;
}
button {
  width: 100px;
  height: 40px;
  font-size: 15px;
  border: 0;
  outline: 1.5px rgb(68, 136, 244) solid;
  border-radius: 5px;
  padding-left: 10px;
  background-color: rgb(164, 199, 255);
}
button:active {
  width: 100px;
  height: 40px;
  font-size: 15px;
  border: 0;
  border-radius: 5px;
  outline: 1.5px rgb(27, 76, 155) solid;
  padding-left: 10px;
  background-color: rgb(68, 136, 244);
}
</style>