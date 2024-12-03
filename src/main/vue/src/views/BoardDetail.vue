<template>
  <form @submit.prevent="submitForm">
    <table>
      <thead>
      <tr>
        <td colspan="2"><h2>*게시글 상세 페이지*</h2></td>
      </tr>
      </thead>
      <tbody>
      <!-- 제목 -->
      <tr>
        <th class="header">제목</th>
        <td>
          <input
              type="text"
              v-if="isEditing"
              v-model="board.title"
              name="title"
              ref="titleInput"
          />
          <span v-else>{{ board.title }}</span>
        </td>
      </tr>

      <!-- 작성자 -->
      <tr>
        <th class="header">작성자</th>
        <td class="styled-text">
          <input
              type="text"
              v-if="isEditing"
              v-model="board.name"
              name="name"
          />
          <span v-else>{{ board.name }}</span>
        </td>
      </tr>

      <!-- 작성날짜 -->
      <tr>
        <th class="header">작성일</th>
        <td class="styled-text">
          <span v-if="!isEditing">{{ formatDate(board.createdDate) }}</span>
          <span v-else>{{ formatDate(board.createdDate) }}</span>
        </td>
      </tr>
      <tr>
        <th class="header">수정일</th>
        <td class="styled-text">
          <span v-if="!isEditing">{{ formatDate(board.updatedDate) }}</span>
          <span v-else>{{ formatDate(board.updatedDate) }}</span>
        </td>
      </tr>
      <!-- 내용 -->
      <tr>
        <th class="header">내용</th>
        <td>
            <textarea
                v-if="isEditing"
                v-model="board.content">
            </textarea>
          <span v-else>{{ board.content }}</span>
        </td>
      </tr>

      <!-- 버튼 -->
      <tr>
        <td></td>
        <td>
          <router-link to="/board-list">
            <input type="button" value="목록" class="back-btn" />
          </router-link>
          <input
              v-if="isEditing"
              type="submit"
              value="수정완료"
              class="update-btn"
              @click="updateBoard"
          />
          <input
              v-if="!isEditing"
              type="button"
              value="수정"
              class="update-btn"
              @click="enableEditing"
          />
          <input
              v-if="isEditing"
              type="button"
              value="수정취소"
              class="delete-btn"
              @click="cancelEditing"
          />
          <input
              v-if="!isEditing"
              type="button"
              value="삭제"
              class="delete-btn"
              @click="deleteBoard"
          />
        </td>
      </tr>
      </tbody>
    </table>
  </form>
</template>

<script>
import axios from 'axios';

export default {
  name: "BoardDetail",
  data() {
    return {
      board: {}, // 데이터 초기화
      originalBoard: {}, // 원본 데이터를 저장하는 변수
      isEditing: false, // 수정 모드 여부
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    getData() {
      const boardId = this.$route.params.id;
      axios.get(`http://localhost:8080/todo/board/detail/${boardId}`)
          .then(response => {
            this.board = response.data;
            this.originalBoard = { ...response.data }; // 원본 데이터를 저장
          })
          .catch(error => {
            console.error('데이터를 가져오는 데 오류가 발생했습니다:', error);
          });
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');
      return `${year}-${month}-${day} ${hours}:${minutes}`;
    },
    enableEditing() {
      this.isEditing = true; // 수정 모드 활성화
      this.originalBoard = { ...this.board }; // 수정 시작 시 원본 데이터 저장
    },
    updateBoard() {
      const boardId = this.$route.params.id;
      axios.put(`http://localhost:8080/todo/board/update/${boardId}`, this.board)
          .then(response => {
            this.board = response.data;
            this.isEditing = false; // 수정 모드 종료
            alert("수정 완료");
          })
          .catch(error => {
            console.error('수정 오류:', error);
          });
    },
    cancelEditing() {
      this.board = { ...this.originalBoard }; // 원본 데이터로 되돌림
      this.isEditing = false; // 수정 모드 종료
    },
    deleteBoard() {
      const boardId = this.$route.params.id;
      if (confirm("정말로 삭제하시겠습니까?")) {
        axios.delete(`http://localhost:8080/todo/board/delete/${boardId}`)
            .then(response => {
              this.board = response.data;
              alert("삭제 완료");
              this.$router.push("/board-list"); // 게시글 목록 페이지로 이동
            })
            .catch(error => {
              console.error('삭제 오류:', error);
            });
      }
    }
  }
};
</script>

<style scoped>
table {
  margin: auto;
  border-spacing: 0 15px;
}

th.header {
  font-weight: bold;
  color: #555;
  text-align: left;
  vertical-align: middle;
  padding-right: 10px;
  width: 70px;
  background-color: transparent;
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

input[type="submit"], input[type="button"] {
  width: 100px;
  height: 40px;
  font-size: 15px;
  border: 0;
  outline: none;
  border-radius: 5px;
  cursor: pointer;
}

input[type="submit"]:active {
  background-color: rgb(68, 136, 244);
}

.back-btn {
  background-color: #a9a9a9; /* 회색 계열 */
  color: white;
  margin-right: 0.5cm;
  border: none; /* 테두리 제거 */
  padding: 8px 16px; /* 버튼 크기 조정 */
  cursor: pointer; /* 마우스 커서 변경 */
  border-radius: 4px; /* 모서리 둥글게 */
}

.back-btn:hover {
  background-color: #808080; /* 호버 시 색상 변경 */
}

.update-btn {
  background-color: #8fbc8f;
  color: white;
  margin-right: 0.5cm;
}

.update-btn:hover {
  background-color: #6fa06e;
}

.delete-btn {
  background-color: #f08080;
  color: white;
}

.delete-btn:hover {
  background-color: #e07070;
}
</style>