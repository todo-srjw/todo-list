<template>
  <form @submit.prevent="submitForm">
    <table>
      <!-- 테이블 헤더 -->
      <thead>
      <tr>
        <td colspan="2"><h2>글쓰기</h2></td>
      </tr>
      </thead>

      <!-- 테이블 본문 -->
      <tbody>
      <!-- Title 필드 -->
      <tr>
        <th class="header">제목</th>
        <td><input type="text" v-model="title" placeholder="제목을 입력하세요" name="title"></td>
      </tr>

      <!-- Comment 필드 -->
      <tr>
        <th class="header">내용</th>
        <td><textarea v-model="content" placeholder="내용을 입력하세요" name="content"></textarea></td>
      </tr>

      <!-- 등록 버튼 -->
      <tr>
        <td></td>
        <td><input type="submit" value="등록"></td>
      </tr>
      </tbody>
    </table>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "BoardWrite",
  data(){
    return{
      title : "",
      content : ""
    };
  },
  methods:{
    async submitForm(){ //서버와의 통신을 비동기 함수로 처리
      const createBoard = {
        title : this.title,
        content : this.content
      };

      try {
        // 데이터를 POST 방식으로 전송
        const response = await axios.post("http://localhost:8080/todo/board/createBoard", createBoard);
        console.log(response.data); // 응답 데이터를 콘솔에 출력

        // 응답 값 활용
        if (response.data === "작성 완료") {
          alert("글 작성이 완료되었습니다!");
          this.$router.push({ name: "BoardList" });
        } else {
          alert("글 작성 중 문제가 발생했습니다.");
        }
      } catch (error) {
        console.error("작성 실패:", error); // 오류 메시지 출력
        alert("작성 실패! 다시 시도해주세요.");
      }
    },
  },
};
</script>

<style scoped>
table {
  margin: auto;
  border-spacing: 0 15px; /* 행 간격 추가 */
}

th.header {
  font-weight: bold;
  color: #555;
  text-align: left;
  vertical-align: middle; /* 입력 필드와 텍스트 정렬 */
  padding-right: 10px; /* 입력 필드와 간격 조정 */
  width: 70px; /* 일정한 폭 지정 */
  background-color: transparent; /* 배경색 제거 */
}

input[type="text"], textarea {
  border: 1.5px rgb(68, 136, 244) solid;
  width: 500px;
  height: 30px;
  border-radius: 5px;
  padding-left: 10px;
}

textarea {
  height: 200px; /* 높이를 적당히 줄임 */
  resize: none;
}

input[type="submit"] {
  width: 100px;
  height: 40px;
  font-size: 15px;
  border: 0;
  outline: 1.5px rgb(68, 136, 244) solid;
  border-radius: 5px;
  background-color: rgb(164, 199, 255);
  cursor: pointer;
}

input[type="submit"]:active {
  outline: 1.5px rgb(27, 76, 155) solid;
  background-color: rgb(68, 136, 244);
}
</style>