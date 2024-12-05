<template>
  <div class="container">
    <h1 class="title">Task List</h1>
    <form @submit.prevent="submitForm">
      <!-- 입력 필드 -->
      <div class="input-container">
        <input
            type="text"
            placeholder="새로운 작업 추가"
            class="task-input"
            v-model="newTask"
        />
        <input
            type="date"
            v-model="newStartDate"
            class="date-input"
        />
        <button class="add-btn" @click="addTask">추가</button>
      </div>
    </form>

    <!-- 작업 목록 -->
    <ul class="task-list">
      <li v-for="(task, index) in tasks" :key="index" class="task-item">
        <div class="task-row">
<!--          <input-->
<!--              type="checkbox"-->
<!--              :checked="task.completed"-->
<!--              @change="toggleTask(index)"-->
<!--          />-->
          <!-- 작업 텍스트 -->
          <span
              v-if="!task.editing"
              :class="{ completed: task.completed }"
              class="task-text"
          >
            {{ task.text }}
          </span>
          <input
              v-if="task.editing"
              type="text"
              v-model="task.text"
              class="task-edit-input"
          />

          <!-- 시작일 -->
          <span class="task-date" v-if="!task.editing">
            시작일: {{ task.startDate || "미정" }}
          </span>
          <input
              v-if="task.editing"
              type="date"
              v-model="task.startDate"
              class="date-edit-input"
          />

          <!-- 버튼 -->
          <button class="edit-btn" @click="toggleEdit(index)">
            {{ task.editing ? "저장" : "수정" }}
          </button>
          <button class="delete-btn" @click="deleteTask(index)">삭제</button>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import axios from "axios";

export default {
  name: "TaskList",
  setup() {
    const newTask = ref(""); // 작업 이름
    const newStartDate = ref(""); // 시작일
    const tasks = ref([]); // 작업 목록

    const getTasks = async () => {
      try {
        const response = await axios.get("http://localhost:8080/todo/task/list");
        // 서버에서 받아온 데이터를 editing 속성을 추가하여 초기화
        tasks.value = response.data.map((task) => ({
          ...task,
          editing: false, // 수정 모드 초기값
        }));
      } catch (error) {
        console.error("Error fetching tasks:", error);
      }
    };

    // 컴포넌트가 마운트될 때 작업 목록을 서버에서 가져옴
    onMounted(() => {
      getTasks();
    });

    const addTask = async () => {
      if (newTask.value.trim() === "") return; // 작업명이 비어있으면 추가하지 않음
      const task = {
        text: newTask.value,
        startDate: newStartDate.value || "미정",
        completed: false,
      };
      try {
        const response = await axios.post("http://localhost:8080/todo/task/createTask",task);
        tasks.value.push({ ...response.data, editing: false }); // 추가된 작업에 editing 속성 추가
        newTask.value = "";
        newStartDate.value = "";
      } catch (error) {
        console.error("Error adding task:", error);
      }
    };

    const deleteTask = async (index) => {
      const task = tasks.value[index];
      if (confirm("정말로 삭제하시겠습니까?")) {
        try {
          // 서버에 DELETE 요청
          await axios.delete(`http://localhost:8080/todo/task/deleteTask/${task.id}`);
          alert("삭제 완료");

          // 최신 데이터 다시 가져오기
          await getTasks(); // 서버에서 작업 목록 갱신
        } catch (error) {
          console.error("삭제 오류:", error);
          alert("삭제 중 오류가 발생했습니다.");
        }
      } else {
        console.log("삭제 취소됨");
      }
    };

    const toggleTask = (index) => {
      tasks.value[index].completed = !tasks.value[index].completed; // 완료 상태 토글
    };

    const toggleEdit = async (index) => {
      const task = tasks.value[index];
      if (task.editing) {
        // 저장 로직
        try {
          const updatedTask = {
            text: task.text, // 수정된 텍스트
            startDate: task.startDate, // 수정된 시작일
          };
          // 서버에 PUT 요청 보내서 데이터를 업데이트
          await axios.put(
              `http://localhost:8080/todo/task/updateTask/${task.id}`,
              updatedTask
          );
          console.log("Task updated:", updatedTask);
        } catch (error) {
          console.error("Error updating task:", error);
        }
      }
      tasks.value[index].editing = !tasks.value[index].editing; // 수정 모드 토글
    };

    return {
      newTask,
      newStartDate,
      tasks,
      addTask,
      deleteTask,
      toggleTask,
      toggleEdit,
    };
  },
};
</script>

<style scoped>
/* 전체 화면 스타일 */
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: flex-start; /* 위쪽부터 시작 */
  height: 100vh;
  background-color: #f0f4f8;
}

/* 컨테이너 스타일 */
.container {
  width: 90%;
  max-width: 1200px;
  background: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* 제목 스타일 */
.title {
  text-align: center;
  margin-bottom: 20px;
  color: #555;
  font-size: 24px;
  font-weight: bold;
}

/* 입력 필드 스타일 */
.input-container {
  display: flex;
  flex-wrap: wrap; /* 줄 바꿈 허용 */
  gap: 10px;
  margin-bottom: 20px;
}

.task-input {
  flex: 3; /* "새로운 작업 추가" 입력 필드의 너비를 더 넓게 */
  min-width: 250px; /* 최소 너비 확대 */
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 5px;
  outline: none;
}

.date-input {
  flex: 1; /* 시작일 입력 필드의 너비 */
  min-width: 150px; /* 최소 너비 설정 */
  padding: 10px;
  font-size: 14px;
  border: 1px solid #ddd;
  border-radius: 5px;
  outline: none;
}

.add-btn {
  padding: 10px 20px;
  background-color: #8a8a8a;
  color: white;
  border: none; /* 테두리 제거 */
  border-radius: 5px;
  cursor: pointer;
  outline: none; /* 포커스 외곽선 제거 */
}

.add-btn:hover {
  background-color: #6f6f6f;
}

/* 작업 목록 스타일 */
.task-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.task-item {
  border-bottom: 1px solid #ddd;
  padding: 10px 0;
}

.task-row {
  display: flex;
  align-items: center;
  gap: 10px;
  flex-wrap: wrap; /* 화면 크기에 따라 줄 바꿈 */
}

.task-text {
  flex: 2;
  word-wrap: break-word; /* 텍스트 줄바꿈 */
}

.task-text.completed {
  text-decoration: line-through;
  color: #aaa;
}

.task-date {
  flex: 1;
  font-size: 14px;
  color: #555;
}

/* 수정 입력 필드 */
.task-edit-input {
  flex: 3; /* 수정 입력 필드를 넓게 */
  min-width: 250px; /* 최소 너비 확대 */
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 5px;
  outline: none;
}

/* 수정 버튼 */
.edit-btn {
  background-color: #8fbc8f;
  color: white;
  border: none; /* 테두리 제거 */
  border-radius: 5px;
  padding: 5px 10px;
  cursor: pointer;
  outline: none; /* 포커스 외곽선 제거 */
}

.edit-btn:hover {
  background-color: #6fa06e;
}

/* 삭제 버튼 */
.delete-btn {
  background-color: #f08080;
  color: white;
  border: none; /* 테두리 제거 */
  border-radius: 5px;
  padding: 5px 10px;
  cursor: pointer;
  outline: none; /* 포커스 외곽선 제거 */
}

.delete-btn:hover {
  background-color: #e07070;
}

/* 반응형 스타일 */
@media (max-width: 768px) {
  .task-row {
    flex-direction: column; /* 작은 화면에서는 세로로 정렬 */
    align-items: flex-start;
  }
}
</style>